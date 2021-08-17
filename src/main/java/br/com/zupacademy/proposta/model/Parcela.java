package br.com.zupacademy.proposta.model;

import javax.persistence.*;

@Entity
public class Parcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idParcela;
    private Integer quantidade;
    private Number valor;
    @ManyToOne
    private Cartao cartao;

    public Parcela(String idParcela, Integer quantidade, Number valor) {
        this.idParcela = idParcela;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getIdParcela() {
        return idParcela;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Number getValor() {
        return valor;
    }
}
