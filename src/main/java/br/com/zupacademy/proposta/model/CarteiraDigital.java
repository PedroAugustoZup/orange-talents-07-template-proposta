package br.com.zupacademy.proposta.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;

@Entity
public class CarteiraDigital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPk;
    private String id;
    private String email;
    private String associadaEm;
    private String emissor;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public CarteiraDigital() {
    }

    public CarteiraDigital(String email, String emissor) {
        this.email = email;
        this.emissor = emissor;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CarteiraDigital(String idCarteiraDigital, String email, String associadaEm, String emissor) {
        this.id = idCarteiraDigital;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public String getIdCarteiraDigital() {
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

    public void setIdCarteiraDigital(String id) {
        this.id = id;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public Long getIdPk() {
        return idPk;
    }
}
