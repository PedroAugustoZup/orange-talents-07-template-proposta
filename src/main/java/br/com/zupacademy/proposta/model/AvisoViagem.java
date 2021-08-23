package br.com.zupacademy.proposta.model;

import br.com.zupacademy.proposta.config.validator.bean.LocalDateVerify;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;

@Entity
public class AvisoViagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @LocalDateVerify
    private LocalDate validoAte;
    @NotBlank
    private String destino;
    @NotBlank
    private String ipCliente;
    @NotBlank
    private String usuarioCliente;
    private Instant momentoAviso;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoViagem() {
    }

    public AvisoViagem(LocalDate validoAte, String destino, String ipCliente,
                       String usuarioCliente, Cartao cartao) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.cartao = cartao;
        this.ipCliente = ipCliente;
        this.usuarioCliente = usuarioCliente;
        this.momentoAviso = Instant.now();
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
