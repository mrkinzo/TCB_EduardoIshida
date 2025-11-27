package br.edu.ifpr.model;

public class Site {
    private int idsite;
    private String nome;
    private String cidade;
    private String pais;
    private String propriedadeprivada; 

    public Site(String string, int i, String string2, String string3, boolean b, boolean c) {
    }

    public Site(String nome, int idsite, String pais, String cidade, String propriedadeprivada) {
        this.nome = nome;
        this.idsite = idsite;
        this.pais = pais;
        this.cidade = cidade;
        this.propriedadeprivada = propriedadeprivada;
    }

    public int getIdsite() {
        return idsite;
    }

    public void setIdsite(int idsite) {
        this.idsite = idsite;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPropriedadeprivada() {
        return propriedadeprivada;
    }

    public void setPropriedadeprivada(String propriedadeprivada) {
        this.propriedadeprivada = propriedadeprivada;
    }

