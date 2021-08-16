package br.com.zupacademy.proposta.controller;

import br.com.zupacademy.proposta.clients.AnaliseFinanceiraClient;
import br.com.zupacademy.proposta.dto.requeste.PropostaRequest;
import br.com.zupacademy.proposta.model.Proposta;
import br.com.zupacademy.proposta.model.enums.EstadoProposta;
import br.com.zupacademy.proposta.repository.PropostaRepository;
import br.com.zupacademy.proposta.service.TransactionalEvent;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/proposta")
public class PropostaController {
    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private AnaliseFinanceiraClient analiseFinanceiraClient;

    @Autowired
    private TransactionalEvent transactional;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid PropostaRequest request,
                                    UriComponentsBuilder uriBuilder){
        Proposta proposta = request.toModel();

        if(proposta.verificaDocumentoExistente(propostaRepository))
            return ResponseEntity.status(422).build();

        transactional.execute(()->{
            propostaRepository.save(proposta);
        });
        EstadoProposta estado = verificaDadosFinanceiros(proposta);
        proposta.setEstadoProposta(estado);

        transactional.execute(()->{
            propostaRepository.save(proposta);
        });

        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    private EstadoProposta verificaDadosFinanceiros(Proposta proposta){
        Map<String, Object> request = Map.of("documento", proposta.getDocumento(),
                "nome", proposta.getNome(),
                "idProposta", String.valueOf(proposta.getId()));

        try {
            return analiseFinanceiraClient.analisa(request)
                    .getResultadoSolicitacao()
                    .getEnums();
        }catch (FeignException e){
            return EstadoProposta.NAO_ELEGIVEL;
        }
    }
}
