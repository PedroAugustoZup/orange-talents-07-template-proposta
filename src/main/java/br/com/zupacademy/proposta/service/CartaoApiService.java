package br.com.zupacademy.proposta.service;

import br.com.zupacademy.proposta.clients.CartoesClient;
import br.com.zupacademy.proposta.dto.response.ApiCartaoResponse;
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
import java.util.List;

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
}
