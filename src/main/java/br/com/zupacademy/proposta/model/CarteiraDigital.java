package br.com.zupacademy.proposta.model;

import javax.persistence.*;

@Entity
public class CarteiraDigital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idCarteiraDigital;
    private String email;
    private String associadaEm;
    private String emissor;
    @ManyToOne
    private Cartao cartao;

    public CarteiraDigital(String idCarteiraDigital, String email, String associadaEm, String emissor) {
        this.idCarteiraDigital = idCarteiraDigital;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public String getIdCarteiraDigital() {
        return idCarteiraDigital;
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
