package br.com.zupacademy.proposta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Renegociacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idRenegociacao;
    private Integer quantidade;
    private Number valor;
    private String dataDeCriacao;

    public Renegociacao(String idRenegociacao, Integer quantidade, Number valor, String dataDeCriacao) {
        this.idRenegociacao = idRenegociacao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getIdRenegociacao() {
        return idRenegociacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Number getValor() {
        return valor;
    }

    public String getDataDeCriacao() {
        return dataDeCriacao;
    }
}
