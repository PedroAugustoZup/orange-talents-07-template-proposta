package br.com.zupacademy.proposta.model.enums;

public enum AnaliseFinanceiraEstado {
    COM_RESTRICAO(EstadoProposta.NAO_ELEGIVEL), SEM_RESTRICAO(EstadoProposta.ELEGIVEL);

    private EstadoProposta enums;
    AnaliseFinanceiraEstado(EstadoProposta enums){
        this.enums = enums;
    }

    public EstadoProposta getEnums() {
        return enums;
    }
}
