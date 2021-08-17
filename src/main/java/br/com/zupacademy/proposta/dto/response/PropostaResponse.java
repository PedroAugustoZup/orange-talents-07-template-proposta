package br.com.zupacademy.proposta.dto.response;

import br.com.zupacademy.proposta.model.Endereco;
import br.com.zupacademy.proposta.model.Proposta;
import br.com.zupacademy.proposta.model.enums.EstadoProposta;

import java.math.BigDecimal;

public class PropostaResponse {

    private String documento;

    private String email;

    private String nome;

    private Endereco endereco;

    private BigDecimal salario;

    private EstadoProposta estadoProposta;

    private String numeroCartao;

    public PropostaResponse(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.estadoProposta = proposta.getEstadoProposta();
        this.numeroCartao = proposta.getCartao().getNumeroCartao();
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public EstadoProposta getEstadoProposta() {
        return estadoProposta;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
