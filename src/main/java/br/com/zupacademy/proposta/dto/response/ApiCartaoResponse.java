package br.com.zupacademy.proposta.dto.response;

import java.util.HashSet;
import java.util.Set;

public class ApiCartaoResponse {

    private String id;
    private String emitidoEm;
    private String titular;
    private Set<Bloqueio> bloqueios = new HashSet<>();
    private Set<AvisoViagem> avisos = new HashSet<>();
    private Set<CarteiraDigital> carteiras = new HashSet<>();
    private Set<Parcela> parcelas = new HashSet<>();
    private Integer limite;
    private Renegociacao renegociacao;
    private Vencimento vencimento;
    private String idProposta;


    public ApiCartaoResponse(String id, String emitidoEm, String titular, Set<Bloqueio> bloqueios,
                             Set<AvisoViagem> avisos, Set<CarteiraDigital> carteiras,
                             Set<Parcela> parcelas, Integer limite, Renegociacao renegociacao,
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
}
