package br.com.zupacademy.proposta.dto.requeste;

import br.com.zupacademy.proposta.model.Biometria;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class BiometriaRequest {

    @NotBlank
    private String codigo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BiometriaRequest(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public Biometria toModel() {
        return new Biometria(this.codigo);
    }
}
