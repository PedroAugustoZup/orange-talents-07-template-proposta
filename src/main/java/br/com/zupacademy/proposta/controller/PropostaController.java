package br.com.zupacademy.proposta.controller;

import br.com.zupacademy.proposta.clients.AnaliseFinanceiraClient;
import br.com.zupacademy.proposta.dto.requeste.PropostaRequest;
import br.com.zupacademy.proposta.dto.response.PropostaResponse;
import br.com.zupacademy.proposta.model.Proposta;
import br.com.zupacademy.proposta.repository.PropostaRepository;
import br.com.zupacademy.proposta.service.TransactionalEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

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

        proposta.setEstadoProposta(analiseFinanceiraClient);

        transactional.execute(()->{
            propostaRepository.save(proposta);
        });

        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listar(@PathVariable("id") Long id){
        Optional<Proposta> proposta = propostaRepository.findById(id);

        if(proposta.isEmpty())
            return ResponseEntity.status(404).build();

        return ResponseEntity.ok(new PropostaResponse());
    }
}
