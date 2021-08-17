package br.com.zupacademy.proposta.service;

import br.com.zupacademy.proposta.clients.CartoesClient;
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
            List<Proposta> lista = manager.createQuery("select p from Proposta p where p.idCartao is null and p.estadoProposta = :estadoProposta")
                    .setParameter("estadoProposta", EstadoProposta.ELEGIVEL)
                    .getResultList();
            lista.forEach(item -> {
                if(item.getIdCartao() == null){
                    item.setIdCartao(cartoesClient.salvaNumeroCartao(String.valueOf(item.getId())).getId());
                    propostaRepository.save(item);
                }
            });
        });
    }
}
