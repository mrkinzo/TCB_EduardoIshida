package br.edu.ifpr.model;

public class Mineral {
    private int idminerais;
    private String nome;
    private String tipo;
    private float dureza;
    private String cor;
    private String brilho;
    private String toxicidade;
    private Site site;

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    // Construtor vazio
    public Mineral() {
    }

    // Construtor para INSERT (sem ID)
    public Mineral(String nome, String tipo, float dureza, String cor,
            String brilho, String toxicidade, Site site) {
        this.nome = nome;
        this.tipo = tipo;
        this.dureza = dureza;
        this.cor = cor;
        this.brilho = brilho;
        this.toxicidade = toxicidade;
        this.site = site;
    }

    // construtor para SELECT (com ID)
    public Mineral(int idminerais, String nome, String tipo, float dureza,
            String cor, String brilho, String toxicidade, Site site) {
        this.idminerais = idminerais;
        this.nome = nome;
        this.tipo = tipo;
        this.dureza = dureza;
        this.cor = cor;
        this.brilho = brilho;
        this.toxicidade = toxicidade;
        this.site = site;
    }

    // Getters e Setters
    public int getIdminerais() {
        return idminerais;
    }

    public void setIdminerais(int idminerais) {
        this.idminerais = idminerais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    @Override
    public String toString() {
        return "Mineral{" +
                "id=" + idminerais +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", dureza=" + dureza +
                ", cor='" + cor + '\'' +
                ", brilho='" + brilho + '\'' +
                ", toxicidade='" + toxicidade + '\'' +
                ", site=" + site +
                '}';
    }

    public String exibirDetalhes() {
        return String.format(
                "ID: %d | %s | %s | Dureza: %.1f | Cor: %s | Brilho: %s | Toxicidade: %s",
                idminerais, nome, tipo, dureza, cor, brilho, toxicidade);
    }
}