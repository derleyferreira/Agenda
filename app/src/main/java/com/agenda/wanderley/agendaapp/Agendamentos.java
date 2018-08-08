package com.agenda.wanderley.agendaapp;

import java.util.Date;

public class Agendamentos {

    private Integer ageId;
    private Date ageData;
    private Date ageHora;
    private Integer ageCliente;
    private Integer ageProfissional;
    private Integer ageSituacao;
    private Integer ageFormapgto;
    private String ageObservacoes;

    public Integer getAgeId() {
        return ageId;
    }

    public void setAgeId(Integer ageId) {
        this.ageId = ageId;
    }

    public Date getAgeData() {
        return ageData;
    }

    public void setAgeData(Date ageData) {
        this.ageData = ageData;
    }

    public Date getAgeHora() {
        return ageHora;
    }

    public void setAgeHora(Date ageHora) {
        this.ageHora = ageHora;
    }

    public Integer getAgeCliente() {
        return ageCliente;
    }

    public void setAgeCliente(Integer ageCliente) {
        this.ageCliente = ageCliente;
    }

    public Integer getAgeProfissional() {
        return ageProfissional;
    }

    public void setAgeProfissional(Integer ageProfissional) {
        this.ageProfissional = ageProfissional;
    }

    public Integer getAgeSituacao() {
        return ageSituacao;
    }

    public void setAgeSituacao(Integer ageSituacao) {
        this.ageSituacao = ageSituacao;
    }

    public Integer getAgeFormapgto() {
        return ageFormapgto;
    }

    public void setAgeFormapgto(Integer ageFormapgto) {
        this.ageFormapgto = ageFormapgto;
    }

    public String getAgeObservacoes() {
        return ageObservacoes;
    }

    public void setAgeObservacoes(String ageObservacoes) {
        this.ageObservacoes = ageObservacoes;
    }
}
