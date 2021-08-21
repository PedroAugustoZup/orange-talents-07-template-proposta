package br.com.zupacademy.proposta.dto.response;

public class BloqueioResponse {

    private String resultado;

    @Deprecated
    public BloqueioResponse() {
    }

    public BloqueioResponse(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
