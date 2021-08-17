package br.com.zupacademy.proposta.dto.response;

public class Parcela {
    private String id;
    private Integer quantidade;
    private Number valor;

    public Parcela(String id, Integer quantidade, Number valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
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
}
