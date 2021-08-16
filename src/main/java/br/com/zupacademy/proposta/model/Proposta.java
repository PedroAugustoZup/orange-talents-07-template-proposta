package br.com.zupacademy.proposta.model;

import br.com.zupacademy.proposta.config.validator.bean.Document;
import br.com.zupacademy.proposta.model.enums.EstadoProposta;
import br.com.zupacademy.proposta.repository.PropostaRepository;

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

    @Enumerated(EnumType.STRING)
    private EstadoProposta estadoProposta;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documento, String email, String nome, Endereco endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public void setEstadoProposta(EstadoProposta estadoProposta) {
        this.estadoProposta = estadoProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public boolean verificaDocumentoExistente(PropostaRepository propostaRepository) {
        return propostaRepository.findByDocumento(this.documento).isPresent();
    }
}
