package br.com.zupacademy.proposta.dto.response;

public class Vencimento {
    private String id;
    private Integer dia;
    private String dataDeCriacao;

    public Vencimento(String id, Integer dia, String dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public String getDataDeCriacao() {
        return dataDeCriacao;
    }
}
