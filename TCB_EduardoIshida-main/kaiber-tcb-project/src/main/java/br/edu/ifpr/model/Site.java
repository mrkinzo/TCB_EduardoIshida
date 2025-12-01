package br.edu.ifpr.model;

public class Site {

    private int idsite;
    private String nome;
    private String cidade;
    private String estado;
    private String pais;
    private boolean propriedadeprivada; // "Sim" ou "Não"

    

    public Site() {
    }

    // Construtor usado no SELECT
    public Site(int idsite, String nome, String cidade, String estado, String pais, boolean propriedadeprivada) {
        this.idsite = idsite;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.propriedadeprivada = propriedadeprivada;
    }

    // Construtor usado no RochaDAO INSERT
    public Site(String nome, String cidade, String estado, String pais, boolean propriedadePrivada) {
    
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.propriedadeprivada = propriedadePrivada ;
    }

    // Getters e Setters...
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public boolean  getPropriedadeprivada() {
        return propriedadeprivada;
    }

    public void setPropriedadeprivada(boolean  propriedadeprivada) {
        this.propriedadeprivada = propriedadeprivada;
    }

    @Override
    public String toString() {
        return "Site{" +
                "id=" + idsite +
                ", nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", pais='" + pais + '\'' +
                ", propriedadePrivada='" + propriedadeprivada + '\'' +
                '}';
    }
}
