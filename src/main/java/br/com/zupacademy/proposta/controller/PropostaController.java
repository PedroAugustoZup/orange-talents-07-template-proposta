package br.com.zupacademy.proposta.controller;

import br.com.zupacademy.proposta.dto.requeste.PropostaRequest;
import br.com.zupacademy.proposta.model.Proposta;
import br.com.zupacademy.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/proposta")
public class PropostaController {
    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid PropostaRequest request,
                                    UriComponentsBuilder uriBuilder){
        Proposta proposta = request.toModel();
        if(proposta.verificaDocumentoExistente(propostaRepository))
            return ResponseEntity.status(422).build();

        propostaRepository.save(proposta);

        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
