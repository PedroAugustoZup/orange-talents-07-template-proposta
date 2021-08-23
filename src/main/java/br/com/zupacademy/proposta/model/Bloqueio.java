package br.com.zupacademy.proposta.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idBloqueio;
    private String bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;
    @ManyToOne
    private Cartao cartao;
    private String ipCliente;
    private String userCliente;

    @Deprecated
    public Bloqueio() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Bloqueio(String idBloqueio, String sistemaResponsavel, boolean ativo) {
        this.idBloqueio = idBloqueio;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio(Cartao cartao, String usuario, String ipAddress) {
        this.cartao = cartao;
        this.userCliente = usuario;
        this.ipCliente = ipAddress;
        DateTimeFormatter formatter =
                DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                        .withLocale( Locale.UK )
                        .withZone( ZoneId.systemDefault() );
        DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.from(ZoneOffset.UTC));
        this.bloqueadoEm = formatter.format(Instant.now());
    }


    public String getIdBloqueio() {
        return idBloqueio;
    }

    public String getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
