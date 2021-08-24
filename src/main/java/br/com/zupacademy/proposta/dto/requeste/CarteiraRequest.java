package br.com.zupacademy.proposta.dto.requeste;

import br.com.zupacademy.proposta.model.CarteiraDigital;
import br.com.zupacademy.proposta.model.enums.CarteiraEnum;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraRequest {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private CarteiraEnum carteira;

    public CarteiraRequest(String email, CarteiraEnum carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public CarteiraDigital toModel() {
        return new CarteiraDigital(this.email, this.carteira.getString());
    }
}
