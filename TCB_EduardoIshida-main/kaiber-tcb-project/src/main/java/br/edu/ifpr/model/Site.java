package br.edu.ifpr.model;

public class Site {
    private int idsite;
    private String nome;
    private String cidade;
    private String pais;
    private String propriedadeprivada;

    // ✅ Construtor corrigido
    public Site(String nome, int par, String cidade, String pais, int par1) {
        this.nome = nome;
        this.cidade = cidade;
        this.pais = pais;
        this.propriedadeprivada = propriedadeprivada;
    }

    // Construtor para SELECT
    public Site(int idsite, String nome, String cidade, String pais, String propriedadeprivada) {
        this.idsite = idsite;
        this.nome = nome;
        this.cidade = cidade;
        this.pais = pais;
        this.propriedadeprivada = propriedadeprivada;
    }

    public Site(String string, int int1, String string2, String string3, boolean equalsIgnoreCase, boolean b) {
        // TODO Auto-generated constructor stub
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

}