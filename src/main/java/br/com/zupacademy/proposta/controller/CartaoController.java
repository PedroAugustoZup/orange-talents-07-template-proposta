package br.com.zupacademy.proposta.controller;

import br.com.zupacademy.proposta.dto.requeste.AvisoRequest;
import br.com.zupacademy.proposta.dto.requeste.BiometriaRequest;
import br.com.zupacademy.proposta.dto.requeste.CarteiraRequest;
import br.com.zupacademy.proposta.model.Biometria;
import br.com.zupacademy.proposta.model.Cartao;
import br.com.zupacademy.proposta.model.CarteiraDigital;
import br.com.zupacademy.proposta.repository.CartaoRepository;
import br.com.zupacademy.proposta.service.CartaoApiService;
import br.com.zupacademy.proposta.service.TransactionalEvent;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private TransactionalEvent transactionalEvent;

    @Autowired
    private CartaoApiService cartaoService;

    @Autowired
    private HttpServletRequest servletRequest;

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/{id}/biometria")
    public ResponseEntity<?> salvaBiometria(@PathVariable("id") Long id,
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
            manager.persist(biometriaEntidade);
        });
        URI uri = uriBuilder.path("/biometria/lista/{id}").buildAndExpand(biometriaEntidade.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{id}/bloqueio")
    public ResponseEntity<?> bloqueio(@PathVariable("id") @Valid @NotNull Long id){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()) return ResponseEntity.status(404).build();

        String usuario = servletRequest.getHeader("User-Agent");
        String ipAddress = servletRequest.getRemoteAddr();

        return ResponseEntity.status(cartaoService.bloqueiaCartao(cartao.get(), usuario, ipAddress))
                .build();
    }

    @PostMapping("/{id}/aviso")
    public ResponseEntity<?> avisoViagem(@PathVariable("id") @Valid @NotNull Long id,
                                         @RequestBody @Valid AvisoRequest request){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()) return ResponseEntity.status(404).build();

        String usuario = servletRequest.getHeader("User-Agent");
        String ipAddress = servletRequest.getRemoteAddr();

        return ResponseEntity.status(cartaoService.avisoViagem(usuario, ipAddress,cartao.get(), request))
                .build();
    }

    @PostMapping("/{id}/carteira")
    public ResponseEntity<?> associaCarteira(@PathVariable("id") @Valid @NotNull Long id,
                                             @RequestBody @Valid CarteiraRequest request,
                                             UriComponentsBuilder uriBuilder){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()) return ResponseEntity.status(404).build();

        CarteiraDigital carteira = request.toModel();
        HttpStatus status = cartaoService.associaCarteira(cartao.get(), carteira);
        if(!status.equals(HttpStatus.CREATED)) return ResponseEntity.status(status).build();

        URI uri = uriBuilder.path("/cartao/{id}/carteira").buildAndExpand(carteira.getIdPk())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
