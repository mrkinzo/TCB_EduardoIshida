package br.edu.ifpr.model.view;

import br.edu.ifpr.controller.SiteController;
import br.edu.ifpr.model.Site;
import java.util.Scanner;

public class SiteView {
    private Scanner LER = new Scanner(System.in);
    private SiteController siteCtrl = new SiteController();

    public void cadastrarSite() {
        Site site = new Site();
        cadastrarSite(site);
    }

    public void cadastrarSite(Site site) {
        System.out.println("\n=== CADASTRAR SITE ===");

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

        siteCtrl.cadastrarSite(site);
        System.out.println("Site cadastrado com sucesso! ID: " + site.getIdsite());
    }
}