package com.agenda.wanderley.agendaapp;

import java.math.BigDecimal;

public class Servicos {

    private Integer serId;
    private String serDescricao;
    private Integer serTempoPadrao;

    public Integer getSerId() {
        return serId;
    }

    public void setSerId(Integer serId) {
        this.serId = serId;
    }

    public String getSerDescricao() {
        return serDescricao;
    }

    public void setSerDescricao(String serDescricao) {
        this.serDescricao = serDescricao;
    }

    public Integer getSerTempoPadrao() {
        return serTempoPadrao;
    }

    public void setSerTempoPadrao(Integer serTempoPadrao) {
        this.serTempoPadrao = serTempoPadrao;
    }

    public BigDecimal getSerValorPadrao() {
        return serValorPadrao;
    }

    public void setSerValorPadrao(BigDecimal serValorPadrao) {
        this.serValorPadrao = serValorPadrao;
    }

    private BigDecimal serValorPadrao;



}
