package com.agenda.wanderley.agendaapp;

public class Pessoas {

    private Integer pesId;
    private String pesNome;
    private String pesEmail;
    private String pesSenha;
    private String pesCelular;
    private String pesEndereco;
    private String pesBairro;
    private String pesEstado;
    private Integer pesCidadeId;

    public Pessoas(Integer pesId, String pesNome, String pesEmail, String pesSenha, String pesCelular, String pesEndereco, String pesBairro, String pesEstado, Integer pesCidadeId) {
        this.pesId = pesId;
        this.pesNome = pesNome;
        this.pesEmail = pesEmail;
        this.pesSenha = pesSenha;
        this.pesCelular = pesCelular;
        this.pesEndereco = pesEndereco;
        this.pesBairro = pesBairro;
        this.pesEstado = pesEstado;
        this.pesCidadeId = pesCidadeId;
    }

    public Pessoas(){

    }



    @Override
    public String toString() {
        return "Pessoas{" +
                "pesId=" + pesId +
                ", pesNome='" + pesNome + '\'' +
                ", pesEmail='" + pesEmail + '\'' +
                ", pesSenha='" + pesSenha + '\'' +
                ", pesCelular='" + pesCelular + '\'' +
                ", pesEndereco='" + pesEndereco + '\'' +
                ", pesBairro='" + pesBairro + '\'' +
                ", pesEstado='" + pesEstado + '\'' +
                ", pesCidadeId=" + pesCidadeId +
                '}';
    }

    public Integer getPesId() {
        return pesId;
    }

    public void setPesId(Integer pesId) {
        this.pesId = pesId;
    }

    public String getPesNome() {
        return pesNome;
    }

    public void setPesNome(String pesNome) {
        this.pesNome = pesNome;
    }

    public String getPesEmail() {
        return pesEmail;
    }

    public void setPesEmail(String pesEmail) {
        this.pesEmail = pesEmail;
    }

    public String getPesSenha() {
        return pesSenha;
    }

    public void setPesSenha(String pesSenha) {
        this.pesSenha = pesSenha;
    }

    public String getPesCelular() {
        return pesCelular;
    }

    public void setPesCelular(String pesCelular) {
        this.pesCelular = pesCelular;
    }

    public String getPesEndereco() {
        return pesEndereco;
    }

    public void setPesEndereco(String pesEndereco) {
        this.pesEndereco = pesEndereco;
    }

    public String getPesBairro() {
        return pesBairro;
    }

    public void setPesBairro(String pesBairro) {
        this.pesBairro = pesBairro;
    }

    public String getPesEstado() {
        return pesEstado;
    }

    public void setPesEstado(String pesEstado) {
        this.pesEstado = pesEstado;
    }

    public Integer getPesCidadeId() {
        return pesCidadeId;
    }

    public void setPesCidadeId(Integer pesCidadeId) {
        this.pesCidadeId = pesCidadeId;
    }
}
