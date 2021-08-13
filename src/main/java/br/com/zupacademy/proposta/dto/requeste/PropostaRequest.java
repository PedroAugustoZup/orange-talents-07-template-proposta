package br.com.zupacademy.proposta.dto.requeste;

import br.com.zupacademy.proposta.config.validator.bean.Document;
import br.com.zupacademy.proposta.model.Endereco;
import br.com.zupacademy.proposta.model.Proposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank
    @Document
    private String documento;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
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
    @NotNull
    @Positive
    private BigDecimal salario;

    public PropostaRequest(String documento, String email, String nome, String rua,
                           String bairro, String cidade, String estado, int numero,
                           BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.salario = salario;
    }

    public Proposta toModel() {
        Endereco estado = new Endereco(this.rua, this.bairro, this.cidade, this.estado, this.numero);
        return new Proposta(this.documento, this.email, this.nome, estado, this.salario);
    }
}
