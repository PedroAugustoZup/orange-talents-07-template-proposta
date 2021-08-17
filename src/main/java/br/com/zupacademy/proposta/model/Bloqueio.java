package br.com.zupacademy.proposta.model;

import javax.persistence.*;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idBloqueio;
    private String bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;
    @ManyToOne
    private Cartao cartao;

    public Bloqueio(String idBloqueio, String bloqueadoEm, String sistemaResponsavel, boolean ativo) {
        this.idBloqueio = idBloqueio;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public String getIdBloqueio() {
        return idBloqueio;
    }

    public String getBloqueadoEm() {
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
