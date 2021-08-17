package br.com.zupacademy.proposta.dto.response;

public class CarteiraDigital {
    private String id;
    private String email;
    private String associadaEm;
    private String emissor;

    public CarteiraDigital(String id, String email, String associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }
}
