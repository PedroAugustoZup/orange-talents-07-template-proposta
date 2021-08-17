package br.com.zupacademy.proposta.model;

import br.com.zupacademy.proposta.clients.AnaliseFinanceiraClient;
import br.com.zupacademy.proposta.config.validator.bean.Document;
import br.com.zupacademy.proposta.dto.requeste.VerificaFinanceiroRequest;
import br.com.zupacademy.proposta.model.enums.EstadoProposta;
import br.com.zupacademy.proposta.repository.PropostaRepository;
import feign.FeignException;

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

    @OneToOne(mappedBy = "proposta")
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documento, String email, String nome, Endereco endereco,
                    BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public void setEstadoProposta(AnaliseFinanceiraClient analiseFinanceiraClient) {
        try {
            EstadoProposta estado = analiseFinanceiraClient.analisa(new VerificaFinanceiroRequest(this).getMapa())
                    .getResultadoSolicitacao()
                    .getEnums();
            this.estadoProposta = estado;
        }catch (FeignException e){
            this.estadoProposta = EstadoProposta.NAO_ELEGIVEL;
        }
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public EstadoProposta getEstadoProposta() {
        return estadoProposta;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getEmail() {
        return email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public boolean verificaDocumentoExistente(PropostaRepository propostaRepository) {
        return propostaRepository.findByDocumento(this.documento).isPresent();
    }
}
