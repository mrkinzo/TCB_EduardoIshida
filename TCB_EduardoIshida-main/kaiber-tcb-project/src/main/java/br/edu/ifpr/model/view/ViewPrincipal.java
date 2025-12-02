package br.edu.ifpr.model.view;

import br.edu.ifpr.controller.*;
import br.edu.ifpr.model.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ViewPrincipal {
    private static Scanner LER = new Scanner(System.in);
    private static MineralController mineralCtrl = new MineralController();
    private static RochaController rochaCtrl = new RochaController();
    private static UsuarioController usuarioCtrl = new UsuarioController();
    private static EmprestimoController emprestimoCtrl = new EmprestimoController();
    private static SiteController siteCtrl = new SiteController();
    private static User usuarioLogado;

    public void iniciarSistema() {
        System.out.println("=== SISTEMA KYBER - GERENCIAMENTO GEOLÓGICO ===");
        
        // Registro automático do usuário
        registrarUsuario();

        if (usuarioLogado != null) {
            System.out.println("\n----==== Bem vindo " + usuarioLogado.getName() + " ====----");
            exibirMenuPrincipal();
        } else {
            System.out.println("❌ Erro ao registrar usuário. Encerrando o sistema.");
        }
    }

    private void registrarUsuario() {
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

    public void exibirMenuPrincipal() {
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
            System.out.println("10 - Cadastrar Site");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = LER.nextInt();
            LER.nextLine();

            switch (opcao) {
                case 1:
                    new RochaView().cadastrarRocha();
                    break;
                case 2:
                    new MineralView().cadastrarMineral();
                    break;
                case 3:
                    new EmprestimoView().realizarEmprestimoPorID();
                    break;
                case 4:
                    new EmprestimoView().listarMeusEmprestimos();
                    break;
                case 5:
                    new EmprestimoView().devolverEmprestimo();
                    break;
                case 6:
                    new MineralView().consultarMinerais();
                    break;
                case 7:
                    new RochaView().consultarRochas();
                    break;
                case 8:
                    new RochaView().atualizarRochas();
                    break;
                case 9:
                    new MineralView().atualizarMinerais();
                    break;
                case 10:
                    new SiteView().cadastrarSite();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}