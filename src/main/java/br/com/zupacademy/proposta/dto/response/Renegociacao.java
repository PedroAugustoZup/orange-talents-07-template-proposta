package br.com.zupacademy.proposta.dto.response;

public class Renegociacao {
    private String id;
    private Integer quantidade;
    private Number valor;
    private String dataDeCriacao;

    public Renegociacao(String id, Integer quantidade, Number valor, String dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
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
