package br.com.zupacademy.proposta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vencimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idVencimento;
    private Integer dia;
    private String dataDeCriacao;

    @Deprecated
    public Vencimento() {
    }

    public Vencimento(String idVencimento, Integer dia, String dataDeCriacao) {
        this.idVencimento = idVencimento;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getIdVencimento() {
        return idVencimento;
    }

    public Integer getDia() {
        return dia;
    }

    public String getDataDeCriacao() {
        return dataDeCriacao;
    }
}
