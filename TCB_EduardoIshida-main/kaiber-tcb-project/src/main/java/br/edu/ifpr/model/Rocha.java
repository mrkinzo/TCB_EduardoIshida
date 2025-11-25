package br.edu.ifpr.model;

public class Rocha {
    private int idRochas;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String tipo;
    private String dureza;
    private String corPrincipal;
    private boolean gem;
    private Site site; // Objeto Site completo

    // Construtor para inserção (sem ID) com objeto Site
    public Rocha(int i, String nome, String tipo, String dureza, String corPrincipal, boolean gem, Site site) {
        this.nome = nome;
        this.tipo = tipo;
        this.dureza = dureza;
        this.corPrincipal = corPrincipal;
        this.gem = gem;
        this.site = site;
    }

    // Getters and Setters
    public int getIdRochas() {
        return idRochas;
    }

    public void setIdRochas(int idRochas) {
        this.idRochas = idRochas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDureza() {
        return dureza;
    }

    public void setDureza(String dureza) {
        this.dureza = dureza;
    }

    public String getCorPrincipal() {
        return corPrincipal;
    }

    public void setCorPrincipal(String corPrincipal) {
        this.corPrincipal = corPrincipal;
    }

    public boolean isGem() {
        return gem;
    }

    public void setGem(boolean gem) {
        this.gem = gem;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    // Método auxiliar para obter o ID do site (útil para o BD)
    public int getSiteId() {
        return site != null ? site.getsId() : 0;
    }

}