package br.edu.ifpr.view;

import br.edu.ifpr.controller.RochaController;
import br.edu.ifpr.controller.SiteController;
import br.edu.ifpr.view.SiteView;
import br.edu.ifpr.model.Rocha;
import br.edu.ifpr.model.Site;
import java.util.Scanner;
import java.util.List;

public class RochaView {
    private Scanner LER;
    private RochaController rochaCtrl;
    private SiteController siteCtrl;
    private SiteView SiteView;

    public RochaView() {
        this.LER = new Scanner(System.in);
        this.rochaCtrl = new RochaController();
        this.siteCtrl = new SiteController();
        this.SiteView = new SiteView();
    }

    public void cadastrarRocha() {
        System.out.println(" === CADASTRAR ROCHA ===");
        System.out.println("Onde foi encontrada a rocha?");
        System.out.println("1 - Usar site existente");
        System.out.println("2 - Cadastrar novo site");
        System.out.print("Escolha: ");

        int opcao = LER.nextInt();
        LER.nextLine();

        Site siteEscolhido = null;

        if (opcao == 1) {
            siteCtrl.listarSites();

            System.out.print("Digite o ID do site existente: ");
            int siteId = LER.nextInt();
            LER.nextLine();

            siteEscolhido = siteCtrl.selecionarSitePorID(siteId);

            if (siteEscolhido == null) {
                System.out.println("Site não encontrado.");
                return;
            }

        } else if (opcao == 2) {
            siteEscolhido = SiteView.cadastrarSiteComRetorno();

            if (siteEscolhido == null) {
                System.out.println("Erro ao cadastrar site.");
                return;
            }
        } else {
            System.out.println("Opção inválida.");
            return;
        }

        System.out.print("Nome da rocha: ");
        String nome = LER.nextLine();

        System.out.print("Tipo: ");
        String tipo = LER.nextLine();

        System.out.print("Dureza: ");
        String dureza = LER.nextLine();

        System.out.print("Cor principal: ");
        String corPrincipal = LER.nextLine();

        System.out.print("É gema? (true/false): ");
        boolean gem = LER.nextBoolean();
        LER.nextLine();

        Rocha rocha = new Rocha(nome, tipo, dureza, corPrincipal, gem, siteEscolhido);
        rochaCtrl.cadastrarRocha(rocha);

        System.out.println("Rocha cadastrada com sucesso.");
    }

    public void consultarRochas() {
        System.out.println(" === CONSULTAR ROCHAS ===");

        List<Rocha> rochas = rochaCtrl.listarTodasRochas();

        if (rochas == null || rochas.isEmpty()) {
            System.out.println("Nenhuma rocha cadastrada.");
        } else {
            System.out.println("Total de rochas: " + rochas.size());
            for (Rocha rocha : rochas) {
                System.out.printf("ID: %d | %s | %s | Dureza: %s | Cor: %s ",
                        rocha.getIdRochas(), rocha.getNome(), rocha.getTipo(),
                        rocha.getDureza(), rocha.getCorPrincipal());
            }
        }
    }

    public void atualizarRochas() {
        System.out.println(" === ATUALIZAR ROCHA ===");

        List<Rocha> rochas = rochaCtrl.listarTodasRochas();

        if (rochas == null || rochas.isEmpty()) {
            System.out.println("Nenhuma rocha cadastrada no sistema.");
            return;
        }

        System.out.println("Rochas disponíveis para atualização:");
        for (Rocha rocha : rochas) {
            System.out.println(rocha.exibirDetalhes());
        }

        System.out.print(" Digite o ID da rocha que deseja atualizar: ");
        int rochaId = LER.nextInt();
        LER.nextLine();

        Rocha rocha = rochaCtrl.buscarRochaPorId(rochaId);

        if (rocha == null) {
            System.out.println("Rocha não encontrada com ID: " + rochaId);
            return;
        }

        System.out.println(" Rocha encontrada:");
        System.out.println(rocha.exibirDetalhes());

        // Coletar novos dados
        System.out.println(" Digite os novos dados (ou Enter para manter o valor atual):");

        System.out.print("Nome [" + rocha.getNome() + "]: ");
        String novoNome = LER.nextLine();
        if (!novoNome.trim().isEmpty()) {
            rocha.setNome(novoNome);
        }

        System.out.print("Tipo [" + rocha.getTipo() + "]: ");
        String novoTipo = LER.nextLine();
        if (!novoTipo.trim().isEmpty()) {
            rocha.setTipo(novoTipo);
        }

        System.out.print("Dureza [" + rocha.getDureza() + "]: ");
        String novaDureza = LER.nextLine();
        if (!novaDureza.trim().isEmpty()) {
            rocha.setDureza(novaDureza);
        }

        System.out.print("Cor principal [" + rocha.getCorPrincipal() + "]: ");
        String novaCor = LER.nextLine();
        if (!novaCor.trim().isEmpty()) {
            rocha.setCorPrincipal(novaCor);
        }

        System.out.print("É gema? [" + (rocha.isGem() ? "Sim" : "Não") + "] (true/false): ");
        String novaGemStr = LER.nextLine();
        if (!novaGemStr.trim().isEmpty()) {
            rocha.setGem(Boolean.parseBoolean(novaGemStr));
        }

        // Atualizar site
        System.out.println(" Deseja alterar o site desta rocha?");
        System.out.println("1 - Manter site atual: " + rocha.getSite().getNome());
        System.out.println("2 - Alterar para outro site");
        System.out.print("Escolha: ");

        int opcaoSite = LER.nextInt();
        LER.nextLine();

        if (opcaoSite == 2) {
            System.out.println(" Sites disponíveis:");
            siteCtrl.listarSites();

            System.out.print("Digite o ID do novo site: ");
            int novoSiteId = LER.nextInt();
            LER.nextLine();

            Site novoSite = siteCtrl.selecionarSitePorID(novoSiteId);
            if (novoSite != null) {
                rocha.setSite(novoSite);
            } else {
                System.out.println("Site não encontrado. Mantendo site atual.");
            }
        }

        // Confirmar
        System.out.println(" --- RESUMO DA ATUALIZAÇÃO ---");
        System.out.println(rocha.exibirDetalhes());
        System.out.print(" Confirmar atualização? (s/n): ");

        String confirmacao = LER.nextLine();
        if (confirmacao.equalsIgnoreCase("s")) {
            rochaCtrl.atualizarRocha(rocha);
            System.out.println("Rocha atualizada com sucesso!");
        } else {
            System.out.println("Atualização cancelada.");
        }
    }

    public void apagarRochas() {
        System.out.println(" === APAGAR ROCHAS ===");

        List<Rocha> rochas = rochaCtrl.listarTodasRochas();

        if (rochas == null || rochas.isEmpty()) {
            System.out.println("Nenhuma rocha cadastrada no sistema.");
            return;
        }

        System.out.println("Rochas disponíveis para exclusão:");
        for (Rocha rocha : rochas) {
            System.out.println(rocha.exibirDetalhes());
        }

        System.out.print(" Digite o ID da rocha que deseja apagar: ");
        int rochaId = LER.nextInt();
        LER.nextLine();

        Rocha rocha = rochaCtrl.buscarRochaPorId(rochaId);

        if (rocha == null) {
            System.out.println("Rocha não encontrada com ID: " + rochaId);
            return;
        }

        System.out.println(" Rocha encontrada:");
        System.out.println(rocha.exibirDetalhes());

        // Confirmar exclusão
        System.out.print(" Confirmar exclusão desta rocha? (s/n): ");
        String confirmacao = LER.nextLine();
        if (confirmacao.equalsIgnoreCase("s")) {
            rochaCtrl.deletarRocha(rochaId);
            System.out.println(" Rocha apagada com sucesso!");
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }
}