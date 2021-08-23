package br.com.zupacademy.proposta.dto.requeste;

import br.com.zupacademy.proposta.config.validator.bean.LocalDateVerify;
import br.com.zupacademy.proposta.model.AvisoViagem;
import br.com.zupacademy.proposta.model.Cartao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoRequest {

    @NotBlank
    private String destino;
    @NotNull
    @LocalDateVerify
    private LocalDate validoAte;

    public AvisoRequest(String destino, LocalDate dataTermino) {
        this.destino = destino;
        this.validoAte = dataTermino;
    }

    public AvisoViagem toModel(String usuario, String ipAddress, Cartao cartao) {
        return new AvisoViagem(this.validoAte, this.destino, ipAddress, usuario, cartao);
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
