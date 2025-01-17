package br.com.zupacademy.proposta.dto.response;

import br.com.zupacademy.proposta.model.enums.AnaliseFinanceiraEstado;

public class ApiFinanceiraResponse {

    private String documento;
    private String nome;
    private AnaliseFinanceiraEstado resultadoSolicitacao;
    private String idProposta;

    public ApiFinanceiraResponse(String documento, String nome, AnaliseFinanceiraEstado resultadoSolicitacao, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public AnaliseFinanceiraEstado getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
