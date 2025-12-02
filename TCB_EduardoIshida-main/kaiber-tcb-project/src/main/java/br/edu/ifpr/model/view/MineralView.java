package br.edu.ifpr.model.view;

import br.edu.ifpr.controller.MineralController;
import br.edu.ifpr.controller.SiteController;
import br.edu.ifpr.model.Mineral;
import br.edu.ifpr.model.Site;
import java.util.Scanner;
import java.util.List;

public class MineralView {
    private Scanner LER = new Scanner(System.in);
    private MineralController mineralCtrl = new MineralController();
    private SiteController siteCtrl = new SiteController();

    public void cadastrarMineral() {
        System.out.println("\n=== CADASTRAR MINERAL ===");
        System.out.println("Onde foi encontrado o mineral?");
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
            Site novoSite = new Site();
            new SiteView().cadastrarSite(novoSite);
            siteEscolhido = novoSite;
        } else {
            System.out.println("Opção inválida.");
            return;
        }

        System.out.print("Nome do mineral: ");
        String nome = LER.nextLine();

        System.out.print("Tipo: ");
        String tipo = LER.nextLine();

        System.out.print("Dureza (0-10): ");
        float dureza = LER.nextFloat();
        LER.nextLine();

        System.out.print("Cor: ");
        String cor = LER.nextLine();

        System.out.print("Brilho: ");
        String brilho = LER.nextLine();

        System.out.print("Toxicidade: ");
        String toxicidade = LER.nextLine();

        Mineral mineral = new Mineral(nome, tipo, dureza, cor, brilho, toxicidade, siteEscolhido);
        mineralCtrl.cadastrarMineral(mineral);

        System.out.println("Mineral cadastrado com sucesso.");
    }

    public void consultarMinerais() {
        System.out.println("\n=== CONSULTAR MINERAIS ===");

        List<Mineral> minerais = mineralCtrl.listarTodosMinerais();

        if (minerais == null || minerais.isEmpty()) {
            System.out.println("Nenhum mineral cadastrado.");
        } else {
            System.out.println("Total de minerais: " + minerais.size());
            for (Mineral mineral : minerais) {
                System.out.printf("ID: %d | %s | %s | Dureza: %.1f | Cor: %s\n",
                        mineral.getIdminerais(), mineral.getNome(), mineral.getTipo(),
                        mineral.getDureza(), mineral.getCor());
            }
        }
    }

    public void atualizarMinerais() {
        System.out.println("\n=== ATUALIZAR MINERAL ===");
        
        List<Mineral> minerais = mineralCtrl.listarTodosMinerais();
        
        if (minerais == null || minerais.isEmpty()) {
            System.out.println("Nenhum mineral cadastrado no sistema.");
            return;
        }
        
        System.out.println("Minerais disponíveis para atualização:");
        for (Mineral mineral : minerais) {
            System.out.println(mineral.exibirDetalhes());
        }
        
        System.out.print("\nDigite o ID do mineral que deseja atualizar: ");
        int mineralId = LER.nextInt();
        LER.nextLine();
        
        Mineral mineral = mineralCtrl.buscarMineralPorId(mineralId);
        
        if (mineral == null) {
            System.out.println("Mineral não encontrado com ID: " + mineralId);
            return;
        }
        
        System.out.println("\nMineral encontrado:");
        System.out.println(mineral.exibirDetalhes());
        
        // Coletar novos dados
        System.out.println("\nDigite os novos dados (ou Enter para manter o valor atual):");
        
        System.out.print("Nome [" + mineral.getNome() + "]: ");
        String novoNome = LER.nextLine();
        if (!novoNome.trim().isEmpty()) {
            mineral.setNome(novoNome);
        }
        
        System.out.print("Tipo [" + mineral.getTipo() + "]: ");
        String novoTipo = LER.nextLine();
        if (!novoTipo.trim().isEmpty()) {
            mineral.setTipo(novoTipo);
        }
        
        System.out.print("Dureza [" + mineral.getDureza() + "]: ");
        String novaDurezaStr = LER.nextLine();
        if (!novaDurezaStr.trim().isEmpty()) {
            try {
                mineral.setDureza(Float.parseFloat(novaDurezaStr));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido para dureza. Mantendo valor atual.");
            }
        }
        
        System.out.print("Cor [" + mineral.getCor() + "]: ");
        String novaCor = LER.nextLine();
        if (!novaCor.trim().isEmpty()) {
            mineral.setCor(novaCor);
        }
        
        System.out.print("Brilho [" + mineral.getBrilho() + "]: ");
        String novoBrilho = LER.nextLine();
        if (!novoBrilho.trim().isEmpty()) {
            mineral.setBrilho(novoBrilho);
        }
        
        System.out.print("Toxicidade [" + mineral.getToxicidade() + "]: ");
        String novaToxicidade = LER.nextLine();
        if (!novaToxicidade.trim().isEmpty()) {
            mineral.setToxicidade(novaToxicidade);
        }
        
        // Atualizar site
        System.out.println("\nDeseja alterar o site deste mineral?");
        System.out.println("1 - Manter site atual: " + mineral.getSite().getNome());
        System.out.println("2 - Alterar para outro site");
        System.out.print("Escolha: ");
        
        int opcaoSite = LER.nextInt();
        LER.nextLine();
        
        if (opcaoSite == 2) {
            System.out.println("\nSites disponíveis:");
            siteCtrl.listarSites();
            
            System.out.print("Digite o ID do novo site: ");
            int novoSiteId = LER.nextInt();
            LER.nextLine();
            
            Site novoSite = siteCtrl.selecionarSitePorID(novoSiteId);
            if (novoSite != null) {
                mineral.setSite(novoSite);
            } else {
                System.out.println("Site não encontrado. Mantendo site atual.");
            }
        }
        
        // Confirmar
        System.out.println("\n--- RESUMO DA ATUALIZAÇÃO ---");
        System.out.println(mineral.exibirDetalhes());
        System.out.print("\nConfirmar atualização? (s/n): ");
        
        String confirmacao = LER.nextLine();
        if (confirmacao.equalsIgnoreCase("s")) {
            mineralCtrl.atualizarMineral(mineral);
            System.out.println("Mineral atualizado com sucesso!");
        } else {
            System.out.println("Atualização cancelada.");
        }
    }
}