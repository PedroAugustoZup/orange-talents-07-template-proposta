package br.com.zupacademy.proposta.dto.response;

import br.com.zupacademy.proposta.model.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApiCartaoResponse {

    private String id;
    private String emitidoEm;
    private String titular;
    private List<Bloqueio> bloqueios;
    private List<AvisoViagem> avisos;
    private Set<CarteiraDigital> carteiras = new HashSet<>();
    private List<Parcela> parcelas;
    private Integer limite;
    private Renegociacao renegociacao;
    private Vencimento vencimento;
    private String idProposta;


    public ApiCartaoResponse(String id, String emitidoEm, String titular, List<Bloqueio> bloqueios,
                             List<AvisoViagem> avisos, Set<CarteiraDigital> carteiras,
                             List<Parcela> parcelas, Integer limite, Renegociacao renegociacao,
                             Vencimento vencimento, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }


    public Cartao toModel(Proposta proposta) {
        return new Cartao(id, emitidoEm, titular, carteiras, parcelas, limite, renegociacao, vencimento, proposta);
    }
}
