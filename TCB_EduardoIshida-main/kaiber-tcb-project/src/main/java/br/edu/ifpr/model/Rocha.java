package br.edu.ifpr.model; 

public class Rocha {
    private int idRochas;
    private String tipo;
    private String dureza;
    private String corPrincipal;
    private boolean gem; // Changed from ignea
    private int siteIdSite;

    // All-fields constructor
    public Rocha(int idRochas, String tipo, String dureza, String corPrincipal, boolean gem, int siteIdSite) {
        this.idRochas = idRochas;
        this.tipo = tipo;
        this.dureza = dureza;
        this.corPrincipal = corPrincipal;
        this.gem = gem;
        this.siteIdSite = siteIdSite;
    }

    // Constructor for insertion
    public Rocha(String tipo, String dureza, String corPrincipal, boolean gem, int siteIdSite) {
        this.tipo = tipo;
        this.dureza = dureza;
        this.corPrincipal = corPrincipal;
        this.gem = gem;
        this.siteIdSite = siteIdSite;
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
    
    public boolean isGem() { // Changed from isIgnea()
        return gem;
    }
    
    public void setGem(boolean gem) { // Changed from setIgnea()
        this.gem = gem;
    }

    public int getSiteIdSite() {
        return siteIdSite;
    }

    public void setSiteIdSite(int siteIdSite) {
        this.siteIdSite = siteIdSite;
    }
}