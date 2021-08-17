package br.com.zupacademy.proposta.dto.response;

public class AvisoViagem {
    private String validoAte;
    private String destino;

    public AvisoViagem(String validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public String getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
