package br.edu.ifpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.ifpr.controller.EmprestimoController;
import br.edu.ifpr.controller.MineralController;
import br.edu.ifpr.controller.RochaController;
import br.edu.ifpr.controller.SiteController;
import br.edu.ifpr.controller.UsuarioController;
import br.edu.ifpr.model.Emprestimo;
import br.edu.ifpr.model.Mineral;
import br.edu.ifpr.model.Rocha;
import br.edu.ifpr.model.Site;
import br.edu.ifpr.model.User;

public class Main {
    private static Scanner LER = new Scanner(System.in);
    private static MineralController mineralCtrl = new MineralController();
    private static RochaController rochaCtrl = new RochaController();
    private static UsuarioController usuarioCtrl = new UsuarioController();
    private static EmprestimoController emprestimoCtrl = new EmprestimoController();
    private static SiteController siteCtrl = new SiteController();

    static User usuarioLogado = null;

    public static void main(String[] args) {
        System.out.println("=== SISTEMA KYBER - GERENCIAMENTO GEOLÓGICO ===");

        while (usuarioLogado == null) {
            System.out.println("\n=== LOGIN / REGISTRO ===");
            System.out.println("1 - Fazer login (usuário existente)");
            System.out.println("2 - Registrar novo usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = LER.nextInt();
            LER.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    registrarUsuario();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println(" Opção inválida!");
            }
        }

        // Se chegou aqui, usuário está logado
        System.out.println("\n----==== Bem vindo " + usuarioLogado.getName() + " ====----");
        exibirMenuPrincipal();
        LER.close();
    }

    public static void fazerLogin() {
        System.out.println("\n=== LOGIN ===");

        // Listar usuários existentes
        usuarioCtrl.listarUsers();

        System.out.print("\nDigite o ID do seu usuário para login: ");
        int userId = LER.nextInt();
        LER.nextLine(); // Limpar buffer

        usuarioLogado = usuarioCtrl.selecionarPorID(userId);

        if (usuarioLogado != null) {
            System.out.println(" Login realizado com sucesso!");
        } else {
            System.out.println("Usuário não encontrado com ID: " + userId);
        }
    }

    public static void registrarUsuario() {
        System.out.println("\n=== REGISTRO DE USUÁRIO ===");
        System.out.println("Por favor, registre-se para usar o sistema:");

        User user = new User();

        System.out.print("Seu nome: ");
        user.setName(LER.nextLine());

        System.out.print("Sua instituição: ");
        user.setInstitution(LER.nextLine());

        System.out.print("Seu cargo: ");
        user.setRole(LER.nextLine());

        // Cadastrar usuário
        usuarioCtrl.cadastrarUser(user);

        if (user.getIduser() > 0) {
            usuarioLogado = user;
            System.out.println("Registro realizado com sucesso! ID: " + user.getIduser());
        } else {
            System.out.println("Erro ao registrar usuário!");
        }
    }

    public static void exibirMenuPrincipal() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Cadastrar Rocha");
            System.out.println("2 - Cadastrar Mineral");
            System.out.println("3 - Realizar Empréstimo");
            System.out.println("4 - Listar Meus Empréstimos");
            System.out.println("5 - Devolver Empréstimo");
            System.out.println("6 - Consultar Minerais");
            System.out.println("7 - Consultar Rochas");
            System.out.println("8 - Atualizar Rocha");
            System.out.println("9 - Atualizar Mineral");
            System.out.println("10 - apagar Rochas");
            System.out.println("11 - apagar Minerais");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = LER.nextInt();
            LER.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarRocha();
                    break;
                case 2:
                    cadastrarMineral();
                    break;
                case 3:
                    realizarEmprestimoPorID();
                    break;
                case 4:
                    listarMeusEmprestimos();
                    break;
                case 5:
                    devolverEmprestimo();
                    break;
                case 6:
                    consultarMinerais();
                    break;
                case 7:
                    consultarRochas();
                    break;
                case 8:
                    atualizarRochas();
                    break;
                case 9:
                    atualizarMinerais();
                    break;
                case 10:
                    apagarRochas();
                    break;
                case 11:
                    apagarMinerais();
                    break;
                case 0:
                    System.out.println("Saindo do sistema");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public static void apagarRochas() {
        System.out.println("\n=== APAGAR ROCHAS ===");

        List<Rocha> rochas = rochaCtrl.listarTodasRochas();

        if (rochas == null || rochas.isEmpty()) {
            System.out.println("Nenhuma rocha cadastrada no sistema.");
            return;
        }

        System.out.println("Rochas disponíveis para exclusão:");
        for (Rocha rocha : rochas) {
            System.out.println(rocha.exibirDetalhes());
        }

        System.out.print("\nDigite o ID da rocha que deseja apagar: ");
        int rochaId = LER.nextInt();
        LER.nextLine();

        Rocha rocha = rochaCtrl.buscarRochaPorId(rochaId);

        if (rocha == null) {
            System.out.println("Rocha não encontrada com ID: " + rochaId);
            return;
        }

        System.out.println("\nRocha encontrada:");
        System.out.println(rocha.exibirDetalhes());

        // Confirmar exclusão
        System.out.print("\nConfirmar exclusão desta rocha? (s/n): ");
        String confirmacao = LER.nextLine();
        if (confirmacao.equalsIgnoreCase("s")) {
            rochaCtrl.deletarRocha(rochaId);
            System.out.println("Rocha apagada com sucesso!");
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }

    public static void apagarMinerais() {
        System.out.println("\n=== APAGAR MINERAIS ===");

        List<Mineral> minerais = mineralCtrl.listarTodosMinerais();

        if (minerais == null || minerais.isEmpty()) {
            System.out.println("Nenhum mineral cadastrado no sistema.");
            return;
        }

        System.out.println("Minerais disponíveis para exclusão:");
        for (Mineral mineral : minerais) {
            System.out.println(mineral.exibirDetalhes());
        }

        System.out.print("\nDigite o ID do mineral que deseja apagar: ");
        int mineralId = LER.nextInt();
        LER.nextLine();

        Mineral mineral = mineralCtrl.buscarMineralPorId(mineralId);

        if (mineral == null) {
            System.out.println("Mineral não encontrado com ID: " + mineralId);
            return;
        }

        System.out.println("\nMineral encontrado:");
        System.out.println(mineral.exibirDetalhes());

        // Confirmar exclusão
        System.out.print("\nConfirmar exclusão deste mineral? (s/n): ");
        String confirmacao = LER.nextLine();
        if (confirmacao.equalsIgnoreCase("s")) {
            mineralCtrl.deletarMineral(mineralId);
            System.out.println("Mineral apagado com sucesso!");
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }

    public static void atualizarMinerais() {
        System.out.println("\n=== ATUALIZAR MINERAIS ===");

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

        // Coletar novos dados (similar ao método de rochas)
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

    public static void atualizarRochas() {
        System.out.println("\n=== ATUALIZAR ROCHAS ===");

        // 1. Listar todas as rochas primeiro
        List<Rocha> rochas = rochaCtrl.listarTodasRochas();

        if (rochas == null || rochas.isEmpty()) {
            System.out.println("Nenhuma rocha cadastrada no sistema.");
            return;
        }

        System.out.println("Rochas disponíveis para atualização:");
        for (Rocha rocha : rochas) {
            System.out.println(rocha.exibirDetalhes());
        }

        // 2. Pedir ID da rocha a atualizar
        System.out.print("\nDigite o ID da rocha que deseja atualizar: ");
        int rochaId = LER.nextInt();
        LER.nextLine(); // Limpar buffer

        // 3. Buscar a rocha pelo ID
        Rocha rocha = rochaCtrl.buscarRochaPorId(rochaId);

        if (rocha == null) {
            System.out.println("Rocha não encontrada com ID: " + rochaId);
            return;
        }

        System.out.println("\nRocha encontrada:");
        System.out.println(rocha.exibirDetalhes());

        // 4. Coletar novos dados
        System.out.println("\nDigite os novos dados (ou Enter para manter o valor atual):");

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

        // 5. Atualizar site (opcional)
        System.out.println("\nDeseja alterar o site desta rocha?");
        System.out.println("1 - Manter site atual: " + rocha.getSite().getNome());
        System.out.println("2 - Alterar para outro site");
        System.out.print("Escolha: ");

        int opcaoSite = LER.nextInt();
        LER.nextLine();

        if (opcaoSite == 2) {
            // Listar sites disponíveis
            System.out.println("\nSites disponíveis:");
            // Você precisa implementar um método para listar sites
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

        // 6. Confirmar atualização
        System.out.println("\n--- RESUMO DA ATUALIZAÇÃO ---");
        System.out.println(rocha.exibirDetalhes());
        System.out.print("\nConfirmar atualização? (s/n): ");

        String confirmacao = LER.nextLine();
        if (confirmacao.equalsIgnoreCase("s")) {
            rochaCtrl.atualizarRocha(rocha);
            System.out.println("Rocha atualizada com sucesso!");
        } else {
            System.out.println("Atualização cancelada.");
        }
    }

    public static void cadastrarRocha() {
        System.out.println("\n=== CADASTRAR ROCHA ===");
        System.out.println("Onde foi encontrada a rocha?");
        System.out.println("1 - Usar site existente");
        System.out.println("2 - Cadastrar novo site");
        System.out.print("Escolha: ");

        int opcao = LER.nextInt();
        LER.nextLine(); // limpar buffer

        Site siteEscolhido = null;

        if (opcao == 1) {
            siteCtrl.listarSites();

            System.out.print("Digite o ID do site existente: ");
            int siteId = LER.nextInt();
            LER.nextLine(); // limpar buffer

            siteEscolhido = siteCtrl.selecionarSitePorID(siteId);

            if (siteEscolhido == null) {
                System.out.println("Site não encontrado.");
                return;
            }

        } else if (opcao == 2) {
            Site novoSite = new Site();
            cadastrarSite(novoSite);
            siteEscolhido = novoSite;

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
        LER.nextLine(); // limpar buffer

        Rocha rocha = new Rocha(nome, tipo, dureza, corPrincipal, gem, siteEscolhido);
        rochaCtrl.cadastrarRocha(rocha);

        System.out.println("Rocha cadastrada com sucesso.");
    }

    public static void cadastrarMineral() {
        System.out.println("\n=== CADASTRAR MINERAL ===");
        System.out.println("Onde foi encontrado o mineral?");
        System.out.println("1 - Usar site existente");
        System.out.println("2 - Cadastrar novo site");
        System.out.print("Escolha: ");

        int opcao = LER.nextInt();
        LER.nextLine(); // limpar buffer

        Site siteEscolhido = null;

        if (opcao == 1) {
            siteCtrl.listarSites();

            System.out.print("Digite o ID do site existente: ");
            int siteId = LER.nextInt();
            LER.nextLine(); // limpar buffer

            siteEscolhido = siteCtrl.selecionarSitePorID(siteId);

            if (siteEscolhido == null) {
                System.out.println("Site não encontrado.");
                return;
            }

        } else if (opcao == 2) {
            Site novoSite = new Site();
            cadastrarSite(novoSite);
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

    public static void cadastrarSite(Site site) {
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
        LER.nextLine(); // lim
        // Criar site

        siteCtrl.cadastrarSite(site);
    }

    public static void realizarEmprestimoPorID() {
        System.out.println("\n=== REALIZAR EMPRÉSTIMO ===");
        System.out.println("Usuário: " + usuarioLogado.getName());

        // Verificar se há itens disponíveis
        List<Mineral> todosMinerais = mineralCtrl.listarTodosMinerais();
        List<Rocha> todasRochas = rochaCtrl.listarTodasRochas();

        if ((todosMinerais == null || todosMinerais.isEmpty()) &&
                (todasRochas == null || todasRochas.isEmpty())) {
            System.out.println(" Nenhum item cadastrado no sistema para empréstimo.");
            return;
        }

        // Listar minerais disponíveis
        System.out.println("\n--- MINERAIS DISPONÍVEIS ---");
        if (todosMinerais == null || todosMinerais.isEmpty()) {
            System.out.println("Nenhum mineral cadastrado no sistema.");
        } else {
            for (Mineral mineral : todosMinerais) {
                System.out.printf("ID: %d | %s | %s | Dureza: %.1f | Site: %s\n",
                        mineral.getIdminerais(), mineral.getNome(), mineral.getTipo(),
                        mineral.getDureza(),
                        mineral.getSite() != null ? mineral.getSite().getNome() : "Nenhum");
            }
        }

        // Listar rochas disponíveis
        System.out.println("\n--- ROCHAS DISPONÍVEIS ---");
        if (todasRochas == null || todasRochas.isEmpty()) {
            System.out.println("Nenhuma rocha cadastrada no sistema.");
        } else {
            for (Rocha rocha : todasRochas) {
                System.out.printf("ID: %d | %s | %s | Dureza: %s | Cor: %s | Site: %s\n",
                        rocha.getIdRochas(), rocha.getNome(), rocha.getTipo(),
                        rocha.getDureza(), rocha.getCorPrincipal(),
                        rocha.getSite() != null ? rocha.getSite().getNome() : "Nenhum");
            }
        }

        // Selecionar itens
        List<Mineral> mineraisSelecionados = selecionarMineraisPorID(todosMinerais);
        List<Rocha> rochasSelecionadas = selecionarRochasPorID(todasRochas);

        // Realizar empréstimo
        if (!mineraisSelecionados.isEmpty() || !rochasSelecionadas.isEmpty()) {
            confirmarERealizarEmprestimo(mineraisSelecionados, rochasSelecionadas);
        } else {
            System.out.println(" Nenhum item selecionado. Empréstimo cancelado.");
        }
    }

    private static void confirmarERealizarEmprestimo(List<Mineral> minerais, List<Rocha> rochas) {
        System.out.println("\n--- RESUMO DO EMPRÉSTIMO ---");
        System.out.println("Usuário: " + usuarioLogado.getName() + " (ID: " + usuarioLogado.getIduser() + ")");
        System.out.println("Data do empréstimo: " + java.time.LocalDate.now());
        System.out.println("Data de devolução: " + java.time.LocalDate.now().plusDays(7));

        if (!minerais.isEmpty()) {
            System.out.println("\nMinerais (" + minerais.size() + "):");
            for (Mineral m : minerais) {
                System.out.println("  - " + m.getNome() +
                        " (ID: " + m.getIdminerais() +
                        ") | Site: " + (m.getSite() != null ? m.getSite().getNome() : "Nenhum"));
            }
        }

        if (!rochas.isEmpty()) {
            System.out.println("\nRochas (" + rochas.size() + "):");
            for (Rocha r : rochas) {
                System.out.println("  - " + r.getNome() +
                        " (ID: " + r.getIdRochas() +
                        ") | Site: " + (r.getSite() != null ? r.getSite().getNome() : "Nenhum"));
            }
        }

        System.out.print("\nConfirmar empréstimo? (s/n): ");
        String confirmacao = LER.nextLine().trim().toLowerCase();

        if (confirmacao.equals("s")) {
            try {
                int emprestimoId = emprestimoCtrl.realizarEmprestimo(
                        usuarioLogado.getIduser(),
                        minerais,
                        rochas);

                if (emprestimoId > 0) {
                    System.out.println("Empréstimo realizado com sucesso!");
                    System.out.println("Número do empréstimo: " + emprestimoId);
                    System.out.println("Data de devolução: " + java.time.LocalDate.now().plusDays(7));

                    // Gerar comprovante
                    gerarComprovanteEmprestimo(emprestimoId, minerais, rochas);
                } else {
                    System.out.println("Erro ao realizar empréstimo!");
                }
            } catch (Exception e) {
                System.err.println(" Erro ao processar empréstimo: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Empréstimo cancelado.");
        }
    }

    private static void gerarComprovanteEmprestimo(int emprestimoId, List<Mineral> minerais, List<Rocha> rochas) {
        System.out.println("\n=== COMPROVANTE DE EMPRÉSTIMO ===");
        System.out.println("Número: " + emprestimoId);
        System.out.println("Usuário: " + usuarioLogado.getName());
        System.out.println("Instituição: " + usuarioLogado.getInstitution());
        System.out.println("Data: " + java.time.LocalDate.now());
        System.out.println("Devolução até: " + java.time.LocalDate.now().plusDays(7));
        System.out.println("\nItens emprestados:");

        int contador = 1;
        if (!minerais.isEmpty()) {
            for (Mineral m : minerais) {
                System.out.println(contador + ". Mineral: " + m.getNome() +
                        " | Tipo: " + m.getTipo() +
                        " | ID: " + m.getIdminerais());
                contador++;
            }
        }

        if (!rochas.isEmpty()) {
            for (Rocha r : rochas) {
                System.out.println(contador + ". Rocha: " + r.getNome() +
                        " | Tipo: " + r.getTipo() +
                        " | ID: " + r.getIdRochas());
                contador++;
            }
        }

        System.out.println("\nTotal de itens: " + (minerais.size() + rochas.size()));
        System.out.println("\nObservações:");
        System.out.println("- Mantenha este comprovante para devolução");
        System.out.println("- Itens danificados deverão ser repostos");
        System.out.println("=================================");
    }

    private static List<Mineral> selecionarMineraisPorID(List<Mineral> todosMinerais) {
        List<Mineral> selecionados = new ArrayList<>();

        System.out.print("\nDigite os IDs dos minerais (separados por vírgula) ou 0 para pular: ");
        String input = LER.nextLine().trim();

        if (input.equals("0") || input.isEmpty()) {
            return selecionados;
        }

        String[] ids = input.split(",");
        for (String idStr : ids) {
            try {
                int idProcurado = Integer.parseInt(idStr.trim());

                Mineral mineralEncontrado = mineralCtrl.buscarMineralPorId(idProcurado);

                if (mineralEncontrado != null) {
                    selecionados.add(mineralEncontrado);
                    System.out.println(". Mineral adicionado: " + mineralEncontrado.getNome());
                } else {
                    System.out.println(" Mineral ID " + idProcurado + " não encontrado");
                }

            } catch (NumberFormatException e) {
                System.out.println(" ID inválido: " + idStr);
            }
        }

        return selecionados;
    }

    public static void listarMeusEmprestimos() {
        System.out.println("\n=== MEUS EMPRÉSTIMOS ===");
        System.out.println("Usuário: " + usuarioLogado.getName());

        List<Emprestimo> emprestimos = emprestimoCtrl.buscarEmprestimosPorUsuario(usuarioLogado.getIduser());

        if (emprestimos == null || emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo encontrado.");
        } else {
            for (Emprestimo emp : emprestimos) {
                System.out.println(" Empréstimo #" + emp.getIdemprestimo() +
                        " | Data: " + emp.getDataEmp() +
                        " a " + emp.getDataDev());
            }
        }
    }

    public static void devolverEmprestimo() {
        System.out.println("\n=== DEVOLVER EMPRÉSTIMO ===");

        System.out.print("Digite o ID do empréstimo para devolução: ");

        try {
            int emprestimoId = LER.nextInt();
            LER.nextLine(); // Limpar buffer

            System.out.print("Confirmar devolução do empréstimo #" + emprestimoId + "? (s/n): ");
            String confirmacao = LER.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                emprestimoCtrl.devolverEmprestimo(emprestimoId);
                System.out.println(". Empréstimo devolvido com sucesso!");
            } else {
                System.out.println("Devolução cancelada.");
            }
        } catch (Exception e) {
            System.out.println(" ID inválido!");
        }
    }

    public static void consultarMinerais() {
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

    public static void consultarRochas() {
        System.out.println("\n=== CONSULTAR ROCHAS ===");

        List<Rocha> rochas = rochaCtrl.listarTodasRochas();

        if (rochas == null || rochas.isEmpty()) {
            System.out.println("Nenhuma rocha cadastrada.");
        } else {
            System.out.println(" Total de rochas: " + rochas.size());
            for (Rocha rocha : rochas) {
                System.out.printf("ID: %d | %s | %s | Dureza: %s | Cor: %s\n",
                        rocha.getIdRochas(), rocha.getNome(), rocha.getTipo(),
                        rocha.getDureza(), rocha.getCorPrincipal());
            }
        }
    }

    private static List<Rocha> selecionarRochasPorID(List<Rocha> todasRochas) {
        List<Rocha> selecionados = new ArrayList<>();

        System.out.print("\nDigite os IDs das rochas (separados por vírgula) ou 0 para pular: ");
        String input = LER.nextLine().trim();

        if (input.equals("0") || input.isEmpty()) {
            return selecionados;
        }

        String[] ids = input.split(",");
        for (String idStr : ids) {
            try {
                int idProcurado = Integer.parseInt(idStr.trim());

                Rocha rochaEncontrada = rochaCtrl.buscarRochaPorId(idProcurado);

                if (rochaEncontrada != null) {
                    selecionados.add(rochaEncontrada);
                    System.out.println("Rocha adicionada: " + rochaEncontrada.getNome());
                } else {
                    System.out.println(" Rocha ID " + idProcurado + " não encontrada");
                }

            } catch (NumberFormatException e) {
                System.out.println("ID inválido: " + idStr);
            }
        }

        return selecionados;
    }
}
