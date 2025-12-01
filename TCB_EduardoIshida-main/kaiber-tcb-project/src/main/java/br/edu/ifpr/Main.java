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

    private static User usuarioLogado;

    public static void main(String[] args) {
        System.out.println("=== SISTEMA KYBER - GERENCIAMENTO GEOLÓGICO ===");

        // Registro automático do usuário
        registrarUsuario();

        if (usuarioLogado != null) {
            System.out.println("\n----==== Bem vindo " + usuarioLogado.getName() + " ====----");
            exibirMenuPrincipal();
        } else {
            System.out.println(" Erro ao registrar usuário. Encerrando o sistema.");
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

        usuarioLogado = user;

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
                case 0:
                    System.out.println("Saindo do sistema");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
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

        // Listar minerais disponíveis
        System.out.println("\n--- MINERAIS DISPONÍVEIS ---");
        List<Mineral> todosMinerais = mineralCtrl.listarTodosMinerais();

        if (todosMinerais == null || todosMinerais.isEmpty()) {
            System.out.println("Nenhum mineral cadastrado no sistema.");
        } else {
            for (Mineral mineral : todosMinerais) {
                System.out.printf("ID: %d | %s | %s | Dureza: %.1f\n",
                        mineral.getIdminerais(), mineral.getNome(), mineral.getTipo(),
                        mineral.getDureza());
            }
        }

        // Listar rochas disponíveis
        System.out.println("\n--- ROCHAS DISPONÍVEIS ---");
        List<Rocha> todasRochas = rochaCtrl.listarTodasRochas();

        if (todasRochas == null || todasRochas.isEmpty()) {
            System.out.println("Nenhuma rocha cadastrada no sistema.");
        } else {
            for (Rocha rocha : todasRochas) {
                System.out.printf("ID: %d | %s | %s | Cor: %s\n",
                        rocha.getIdRochas(), rocha.getNome(), rocha.getTipo(),
                        rocha.getCorPrincipal());
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

    private static void confirmarERealizarEmprestimo(List<Mineral> minerais, List<Rocha> rochas) {
        System.out.println("\n--- RESUMO DO EMPRÉSTIMO ---");
        System.out.println("Usuário: " + usuarioLogado.getName() + " (ID: " + usuarioLogado.getIduser() + ")");

        if (!minerais.isEmpty()) {
            System.out.println("\nMinerais (" + minerais.size() + "):");
            for (Mineral m : minerais) {
                System.out.println("  - " + m.getNome() + " (ID: " + m.getIdminerais() + ")");
            }
        }

        if (!rochas.isEmpty()) {
            System.out.println("\nRochas (" + rochas.size() + "):");
            for (Rocha r : rochas) {
                System.out.println("  - " + r.getNome() + " (ID: " + r.getIdRochas() + ")");
            }
        }

        System.out.print("\nConfirmar empréstimo? (s/n): ");
        String confirmacao = LER.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            int emprestimoId = emprestimoCtrl.realizarEmprestimo(
                    usuarioLogado.getIduser(),
                    minerais,
                    rochas);

            if (emprestimoId > 0) {
                System.out.println(". Empréstimo realizado com sucesso!");
                System.out.println("Número do empréstimo: " + emprestimoId);
                System.out.println("Data de devolução: 7 dias a partir de hoje");
            } else {
                System.out.println(" Erro ao realizar empréstimo!");
            }
        } else {
            System.out.println("Empréstimo cancelado.");
        }
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