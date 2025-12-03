package br.edu.ifpr.view;

import br.edu.ifpr.controller.SiteController;
import br.edu.ifpr.model.Site;
import java.util.Scanner;

public class SiteView {
    private Scanner LER;
    private SiteController siteCtrl;

    public SiteView() {
        this.LER = new Scanner(System.in);
        this.siteCtrl = new SiteController();
    }

    public void cadastrarSite() {
        Site site = new Site();
        cadastrarSite(site);
    }

    public Site cadastrarSiteComRetorno() {
        Site site = new Site();
        if (cadastrarSite(site)) {
            return site;
        }
        return null;
    }

    private boolean cadastrarSite(Site site) {
        System.out.println(" === CADASTRAR SITE ===");

        System.out.print("Nome do site: ");
        site.setNome(LER.nextLine());

        System.out.print("Cidade: ");
        site.setCidade(LER.nextLine());

        System.out.print("Estado: ");
        site.setEstado(LER.nextLine());

        System.out.print("País: ");
        site.setPais(LER.nextLine());

        System.out.print("Propriedade privada? (true/false): ");
        site.setPropriedadeprivada(LER.nextBoolean());
        LER.nextLine();

        try {
            siteCtrl.cadastrarSite(site);
            System.out.println("Site cadastrado com sucesso! ID: " + site.getIdsite());
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar site: " + e.getMessage());
            return false;
        }
    }
}