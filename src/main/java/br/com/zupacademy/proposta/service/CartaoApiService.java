package br.com.zupacademy.proposta.service;

import br.com.zupacademy.proposta.clients.CartoesClient;
import br.com.zupacademy.proposta.dto.requeste.AvisoRequest;
import br.com.zupacademy.proposta.dto.requeste.BloqueioRequest;
import br.com.zupacademy.proposta.dto.response.ApiCartaoResponse;
import br.com.zupacademy.proposta.dto.response.BloqueioResponse;
import br.com.zupacademy.proposta.model.*;
import br.com.zupacademy.proposta.model.enums.EstadoProposta;
import br.com.zupacademy.proposta.repository.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public HttpStatus bloqueiaCartao(Cartao cartao, String usuario, String ipAddress){
        for (Bloqueio bloqueio : cartao.getBloqueios()) {
            if(bloqueio.isAtivo()) return HttpStatus.UNPROCESSABLE_ENTITY;
        }
        BloqueioResponse response = cartoesClient.geraBloqueioCartao(cartao.getNumeroCartao(),
                new BloqueioRequest("proposta"));

        if(response.getResultado().equals("BLOQUEADO")){
            Bloqueio bloqueio = new Bloqueio(cartao,usuario, ipAddress);
            transactional.execute(()->{
                manager.persist(bloqueio);
            });
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus avisoViagem(String usuario, String ipAddress, Cartao cartao, @Valid AvisoRequest request) {
        Map<String, String> retorno = cartoesClient.avisoViagem(cartao.getNumeroCartao(),
                request);
        if(!retorno.get("resultado").equals("CRIADO")) return HttpStatus.BAD_REQUEST;

        AvisoViagem avisoViagem = request.toModel(usuario, ipAddress,cartao);
        transactional.execute(()->{
            manager.persist(avisoViagem);
        });
        return HttpStatus.OK;
    }

    public HttpStatus associaCarteira(Cartao cartao, CarteiraDigital carteira) {
        try {
            Map<String, String> retorno = cartoesClient.associaCarteira(cartao.getNumeroCartao(),
                    Map.of("email", carteira.getEmail(), "carteira", carteira.getEmissor()));
            if(!retorno.get("resultado").equals("ASSOCIADA")) return HttpStatus.BAD_REQUEST;
            carteira.setIdCarteiraDigital(retorno.get("id"));
        }catch (FeignException.UnprocessableEntity e){
            return HttpStatus.UNPROCESSABLE_ENTITY;
        }
        transactional.execute(()->{
            carteira.setCartao(cartao);
            manager.persist(carteira);
        });
        return HttpStatus.CREATED;
    }
}
