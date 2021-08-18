package br.com.zupacademy.proposta.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<CarteiraDigital> carteiras = new ArrayList<>();

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

    public Cartao(String id, String emitidoEm, String titular, List<Bloqueio> bloqueios,
                  List<AvisoViagem> avisos, List<CarteiraDigital> carteiras, List<Parcela> parcelas,
                  Integer limite, Renegociacao renegociacao, Vencimento vencimento, Proposta proposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
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

    public List<CarteiraDigital> getCarteiras() {
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
