package br.edu.ifpr.view;

import br.edu.ifpr.model.User;

import java.util.Scanner;

public class ViewPrincipal {
    private Scanner LER;
    private RochaView viewRocha;
    private MineralView viewMineral;
    private EmprestimoView viewEmprestimo;
    private SiteView viewSite;
    private User usuarioLogado;

    // CONSTRUTOR que recebe User
    public ViewPrincipal(User usuarioLogado) {
        this.LER = new Scanner(System.in);
        this.usuarioLogado = usuarioLogado;
        this.viewRocha = new RochaView();
        this.viewMineral = new MineralView();
        this.viewEmprestimo = new EmprestimoView(usuarioLogado); // Passa usuarioLogado para ViewEmprestimo
        this.viewSite = new SiteView();
    }

    public void exibirMenuPrincipal() {
        System.out.println(" ----==== Bem vindo " + usuarioLogado.getName() + " ====----");

        while (true) {
            System.out.println(" === MENU PRINCIPAL ===");
            System.out.println("1 - Cadastrar Rocha");
            System.out.println("2 - Cadastrar Mineral");
            System.out.println("3 - Realizar Empréstimo");
            System.out.println("4 - Listar Meus Empréstimos");
            System.out.println("5 - Devolver Empréstimo");
            System.out.println("6 - Consultar Minerais");
            System.out.println("7 - Consultar Rochas");
            System.out.println("8 - Atualizar Rocha");
            System.out.println("9 - Atualizar Mineral");
            System.out.println("10 - Apagar Rochas");
            System.out.println("11 - Apagar Minerais");
            System.out.println("12 - Cadastrar Site");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = LER.nextInt();
            LER.nextLine();

            switch (opcao) {
                case 1:
                    viewRocha.cadastrarRocha();
                    break;
                case 2:
                    viewMineral.cadastrarMineral();
                    break;
                case 3:
                    viewEmprestimo.realizarEmprestimoPorID();
                    break;
                case 4:
                    viewEmprestimo.listarMeusEmprestimos();
                    break;
                case 5:
                    viewEmprestimo.devolverEmprestimo();
                    break;
                case 6:
                    viewMineral.consultarMinerais();
                    break;
                case 7:
                    viewRocha.consultarRochas();
                    break;
                case 8:
                    viewRocha.atualizarRochas();
                    break;
                case 9:
                    viewMineral.atualizarMinerais();
                    break;
                case 10:
                    viewRocha.apagarRochas();
                    break;
                case 11:
                    viewMineral.apagarMinerais();
                    break;
                case 12:
                    viewSite.cadastrarSite();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    LER.close();
                    return;
                default:
                    System.out.println(" Opção inválida.");
            }
        }
    }
}