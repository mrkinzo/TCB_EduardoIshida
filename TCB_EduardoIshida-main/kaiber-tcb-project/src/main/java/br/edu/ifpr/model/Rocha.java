package br.edu.ifpr.model;

public class Rocha {
    private int idRochas;
    private String nome;
    private String tipo;
    private String dureza;
    private String corPrincipal;
    private boolean gem;
    private Site site;

    // ✅ Construtor vazio
    public Rocha() {}

    // ✅ Construtor para INSERT (sem ID)
    public Rocha(String nome, String tipo, String dureza, String corPrincipal, boolean gem, Site site) {
        this.nome = nome;
        this.tipo = tipo;
        this.dureza = dureza;
        this.corPrincipal = corPrincipal;
        this.gem = gem;
        this.site = site;
    }

    // ✅ Construtor para SELECT (com ID do banco)
    public Rocha(int idRochas, String nome, String tipo, String dureza, String corPrincipal, boolean gem, Site site) {
        this.idRochas = idRochas;
        this.nome = nome;
        this.tipo = tipo;
        this.dureza = dureza;
        this.corPrincipal = corPrincipal;
        this.gem = gem;
        this.site = site;
    }

    // ✅ Getters e Setters
    public int getIdRochas() {
        return idRochas;
    }

    public void setIdRochas(int idRochas) {
        this.idRochas = idRochas;
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

    // ✅ Método auxiliar para obter o ID do site
    public int getSiteId() {
        return site != null ? site.getIdsite() : 0;
    }

    // Método toString para exibição
    @Override
    public String toString() {
        return "Rocha{" +
                "id=" + idRochas +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", dureza='" + dureza + '\'' +
                ", corPrincipal='" + corPrincipal + '\'' +
                ", gem=" + gem +
                ", site=" + (site != null ? site.getNome() : "Nenhum") +
                '}';
    }

    //  Método para exibição formatada
    public String exibirDetalhes() {
        return String.format(
            "ID: %d | %s | %s | Dureza: %s | Cor: %s | Gema: %s | Site: %s",
            idRochas, nome, tipo, dureza, corPrincipal, 
            gem ? "Sim" : "Não", 
            site != null ? site.getNome() : "Nenhum"
        );
    }
}