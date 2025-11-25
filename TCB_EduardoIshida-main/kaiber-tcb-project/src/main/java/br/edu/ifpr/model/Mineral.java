package br.edu.ifpr.model;

public class Mineral {

    public Mineral(int siteIdSite, String tipo, float dureza, String cor, String brilho, String toxicidade, Site site) {
        this.tipo = tipo;
        this.dureza = dureza;
        this.cor = cor;
        this.brilho = brilho;
        this.toxicidade = toxicidade;
    }

    String tipo;
    float dureza;
    String cor;
    String brilho;
    String toxicidade;
    Site site;

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getDureza() {
        return dureza;
    }

    public void setDureza(float dureza) {
        this.dureza = dureza;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getBrilho() {
        return brilho;
    }

    public void setBrilho(String brilho) {
        this.brilho = brilho;
    }

    public String getToxicidade() {
        return toxicidade;
    }

    public void setToxicidade(String toxicidade) {
        this.toxicidade = toxicidade;
    }

    public int getIdminerais() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIdminerais'");
    }

    public void setIdms(int int1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIdms'");
    }

    public void setIdminerais(int int1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIdminerais'");
    }

}
