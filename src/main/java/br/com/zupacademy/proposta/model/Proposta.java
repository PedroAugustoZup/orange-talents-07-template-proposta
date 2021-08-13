package br.com.zupacademy.proposta.model;

import br.com.zupacademy.proposta.config.validator.bean.Document;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_proposta")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Document
    private String documento;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @Embedded
    @NotNull
    private Endereco endereco;
    @NotNull
    @Positive
    private BigDecimal salario;

    public Proposta(String documento, String email, String nome, Endereco endereco,BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }
}
