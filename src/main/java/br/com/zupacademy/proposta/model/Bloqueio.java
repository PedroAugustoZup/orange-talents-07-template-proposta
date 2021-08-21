package br.com.zupacademy.proposta.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idBloqueio;
    private Instant bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;
    @ManyToOne
    private Cartao cartao;
    private String ipCliente;
    private String userCliente;

    @Deprecated
    public Bloqueio() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Bloqueio(String idBloqueio, String sistemaResponsavel, boolean ativo) {
        this.idBloqueio = idBloqueio;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio(Cartao cartao, String usuario, String ipAddress) {
        this.cartao = cartao;
        this.userCliente = usuario;
        this.ipCliente = ipAddress;
        this.bloqueadoEm = Instant.now();
    }


    public String getIdBloqueio() {
        return idBloqueio;
    }

    public Instant getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
