package br.edu.ifpr.view;

import br.edu.ifpr.controller.EmprestimoController;
import br.edu.ifpr.controller.MineralController;
import br.edu.ifpr.controller.RochaController;
import br.edu.ifpr.model.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class EmprestimoView {
    private Scanner LER;
    private EmprestimoController emprestimoCtrl;
    private MineralController mineralCtrl;
    private RochaController rochaCtrl;
    private User usuarioLogado;

    // CONSTRUTOR que recebe User
    public EmprestimoView(User usuarioLogado) {
        this.LER = new Scanner(System.in);
        this.emprestimoCtrl = new EmprestimoController();
        this.mineralCtrl = new MineralController();
        this.rochaCtrl = new RochaController();
        this.usuarioLogado = usuarioLogado;
    }

    public void realizarEmprestimoPorID() {
        if (usuarioLogado == null) {
            System.out.println(" Erro: Usuário não está logado!");
            return;
        }

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

    private void confirmarERealizarEmprestimo(List<Mineral> minerais, List<Rocha> rochas) {
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
                    System.out.println("  Empréstimo realizado com sucesso!");
                    System.out.println("Número do empréstimo: " + emprestimoId);
                    System.out.println("Data de devolução: " + java.time.LocalDate.now().plusDays(7));

                    // Gerar comprovante
                    gerarComprovanteEmprestimo(emprestimoId, minerais, rochas);
                } else {
                    System.out.println(" Erro ao realizar empréstimo!");
                }
            } catch (Exception e) {
                System.err.println(" Erro ao processar empréstimo: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Empréstimo cancelado.");
        }
    }

    private void gerarComprovanteEmprestimo(int emprestimoId, List<Mineral> minerais, List<Rocha> rochas) {
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

    private List<Mineral> selecionarMineraisPorID(List<Mineral> todosMinerais) {
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
                    System.out.println("  Mineral adicionado: " + mineralEncontrado.getNome());
                } else {
                    System.out.println(" Mineral ID " + idProcurado + " não encontrado");
                }

            } catch (NumberFormatException e) {
                System.out.println(" ID inválido: " + idStr);
            }
        }

        return selecionados;
    }

    private List<Rocha> selecionarRochasPorID(List<Rocha> todasRochas) {
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
                    System.out.println("  Rocha adicionada: " + rochaEncontrada.getNome());
                } else {
                    System.out.println(" Rocha ID " + idProcurado + " não encontrada");
                }

            } catch (NumberFormatException e) {
                System.out.println(" ID inválido: " + idStr);
            }
        }

        return selecionados;
    }

    public void listarMeusEmprestimos() {
        if (usuarioLogado == null) {
            System.out.println(" Erro: Usuário não está logado!");
            return;
        }

        System.out.println("\n=== MEUS EMPRÉSTIMOS ===");
        System.out.println("Usuário: " + usuarioLogado.getName());

        List<Emprestimo> emprestimos = emprestimoCtrl.buscarEmprestimosPorUsuario(usuarioLogado.getIduser());

        if (emprestimos == null || emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo encontrado.");
        } else {
            for (Emprestimo emp : emprestimos) {
                System.out.println("  Empréstimo #" + emp.getIdemprestimo() +
                        " | Data: " + emp.getDataEmp() +
                        " a " + emp.getDataDev());
            }
        }
    }

    public void devolverEmprestimo() {
        if (usuarioLogado == null) {
            System.out.println(" Erro: Usuário não está logado!");
            return;
        }

        System.out.println("\n=== DEVOLVER EMPRÉSTIMO ===");

        System.out.print("Digite o ID do empréstimo para devolução: ");

        try {
            int emprestimoId = LER.nextInt();
            LER.nextLine();

            System.out.print("Confirmar devolução do empréstimo #" + emprestimoId + "? (s/n): ");
            String confirmacao = LER.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                emprestimoCtrl.devolverEmprestimo(emprestimoId);
                System.out.println("  Empréstimo devolvido com sucesso!");
            } else {
                System.out.println("Devolução cancelada.");
            }
        } catch (Exception e) {
            System.out.println(" ID inválido!");
        }
    }
}