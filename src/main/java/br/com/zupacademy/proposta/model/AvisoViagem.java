package br.com.zupacademy.proposta.model;

import javax.persistence.*;

@Entity
public class AvisoViagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String validoAte;
    private String destino;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoViagem() {
    }

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
