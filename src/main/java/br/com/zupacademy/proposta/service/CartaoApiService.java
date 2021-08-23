package br.com.zupacademy.proposta.service;

import br.com.zupacademy.proposta.clients.CartoesClient;
import br.com.zupacademy.proposta.dto.requeste.AvisoRequest;
import br.com.zupacademy.proposta.dto.requeste.BloqueioRequest;
import br.com.zupacademy.proposta.dto.response.ApiCartaoResponse;
import br.com.zupacademy.proposta.dto.response.BloqueioResponse;
import br.com.zupacademy.proposta.model.AvisoViagem;
import br.com.zupacademy.proposta.model.Bloqueio;
import br.com.zupacademy.proposta.model.Cartao;
import br.com.zupacademy.proposta.model.Proposta;
import br.com.zupacademy.proposta.model.enums.EstadoProposta;
import br.com.zupacademy.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Service
@EnableScheduling
public class CartaoApiService {
    @Autowired
    private PropostaRepository propostaRepository;

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CartoesClient cartoesClient;

    @Autowired
    private TransactionalEvent transactional;

    private static final long delay = 1000*10;

    @Scheduled(fixedDelay = delay)
    public void buscaCartao(){
        transactional.execute(()->{
            List<Proposta> lista = propostaRepository.findByCartaoIsNullAndEstadoPropostaIs(EstadoProposta.ELEGIVEL);
            lista.forEach(proposta -> {
                if(proposta.getCartao() == null){
                    ApiCartaoResponse cartaoResponse = cartoesClient.salvaNumeroCartao(String.valueOf(proposta.getId()));
                    Cartao cartao = cartaoResponse.toModel(proposta);
                    manager.persist(cartao);
                }
            });
        });
    }

    public int bloqueiaCartao(Cartao cartao, String usuario, String ipAddress){
        for (Bloqueio bloqueio : cartao.getBloqueios()) {
            if(bloqueio.isAtivo()) return 422;
        }
        BloqueioResponse response = cartoesClient.geraBloqueioCartao(cartao.getNumeroCartao(),
                new BloqueioRequest("proposta"));

        if(response.getResultado().equals("BLOQUEADO")){
            Bloqueio bloqueio = new Bloqueio(cartao,usuario, ipAddress);
            transactional.execute(()->{
                manager.persist(bloqueio);
            });
            return 200;
        }
        return 400;
    }

    public int avisoViagem(String usuario, String ipAddress, Cartao cartao, @Valid AvisoRequest request) {
        Map<String, String> retorno = cartoesClient.avisoViagem(cartao.getNumeroCartao(),
                request);
        if(!retorno.get("resultado").equals("CRIADO")) return 400;

        AvisoViagem avisoViagem = request.toModel(usuario, ipAddress,cartao);
        transactional.execute(()->{
            manager.persist(avisoViagem);
        });
        return 200;
    }
}
