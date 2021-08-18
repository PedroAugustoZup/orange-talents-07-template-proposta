package br.com.zupacademy.proposta.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_biometria")
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    private String codigo;

    @ManyToOne
    private Cartao cartao;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Biometria(String codigo) {
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
