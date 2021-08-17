package br.com.zupacademy.proposta.dto.response;

public class Bloqueio {

    private String id;
    private String bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;

    public Bloqueio(String id, String bloqueadoEm, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public String getId() {
        return id;
    }

    public String getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
