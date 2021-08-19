package br.com.zupacademy.proposta.controller;

import br.com.zupacademy.proposta.dto.requeste.BiometriaRequest;
import br.com.zupacademy.proposta.model.Biometria;
import br.com.zupacademy.proposta.model.Cartao;
import br.com.zupacademy.proposta.repository.BiometriaRepository;
import br.com.zupacademy.proposta.repository.CartaoRepository;
import br.com.zupacademy.proposta.service.TransactionalEvent;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private TransactionalEvent transactionalEvent;

    @RolesAllowed("user")
    @PostMapping("/biometria/{id}")
    public ResponseEntity<?> salvaBiometria(@PathVariable("id")Long id,
                                            @RequestBody @Valid BiometriaRequest request,
                                            UriComponentsBuilder uriBuilder){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty())
            return ResponseEntity.status(404).build();

        if(!Base64.isBase64(request.getCodigo()))
            return ResponseEntity.status(400).build();

        Biometria biometriaEntidade = request.toModel();
        biometriaEntidade.setCartao(cartao.get());
        transactionalEvent.execute(()->{
            biometriaRepository.save(biometriaEntidade);
        });
        URI uri = uriBuilder.path("/biometria/lista/{id}").buildAndExpand(biometriaEntidade.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
