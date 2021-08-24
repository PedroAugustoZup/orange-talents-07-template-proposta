package br.com.zupacademy.proposta.model.enums;

public enum CarteiraEnum {
    PAYPAL("paypal");
    private String descricao;

    CarteiraEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getString() {
        return descricao;
    }
}
