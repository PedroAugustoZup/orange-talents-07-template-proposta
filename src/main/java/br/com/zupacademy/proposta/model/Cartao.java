package br.com.zupacademy.proposta.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPk;
    private String id;
    private String emitidoEm;
    private String titular;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<Bloqueio> bloqueios = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<AvisoViagem> avisos = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private Set<CarteiraDigital> carteiras = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<Parcela> parcelas = new ArrayList<>();

    private Integer limite;
    @OneToOne
    private Renegociacao renegociacao;
    @OneToOne(cascade = CascadeType.ALL)
    private Vencimento vencimento;
    @OneToOne(cascade = CascadeType.ALL)
    private Proposta proposta;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<Biometria> biometria;

    @Deprecated
    public Cartao() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Cartao(String id, String emitidoEm, String titular, Set<CarteiraDigital> carteiras,
                  List<Parcela> parcelas,
                  Integer limite, Renegociacao renegociacao, Vencimento vencimento, Proposta proposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.proposta = proposta;
    }

    public Long getIdPk() {
        return idPk;
    }

    public String getNumeroCartao() {
        return id;
    }

    public String getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public List<AvisoViagem> getAvisos() {
        return avisos;
    }

    public Set<CarteiraDigital> getCarteiras() {
        return carteiras;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public Integer getLimite() {
        return limite;
    }

    public Renegociacao getRenegociacao() {
        return renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }
}
