package br.com.zupacademy.proposta.dto.requeste;

import br.com.zupacademy.proposta.model.Proposta;

import java.util.Map;

public class VerificaFinanceiroRequest {

    private Map<String, Object> mapa;

    public VerificaFinanceiroRequest(Proposta proposta) {
        this.mapa = Map.of("documento", proposta.getDocumento(),
                "nome", proposta.getNome(),
                "idProposta", String.valueOf(proposta.getId()));
    }

    public Map<String, Object> getMapa() {
        return mapa;
    }
}
