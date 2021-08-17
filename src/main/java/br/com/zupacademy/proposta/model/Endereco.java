package br.com.zupacademy.proposta.model;

import javax.persistence.Embeddable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
@Valid
public class Endereco {
    @NotBlank
    private String rua;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String estado;
    @NotNull
    private int numero;

    @Deprecated
    public Endereco() {
    }

    public Endereco(@Valid @NotBlank String rua, @NotBlank String bairro, @NotBlank String cidade, @NotBlank String estado, @NotNull int numero) {
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public int getNumero() {
        return numero;
    }
}
