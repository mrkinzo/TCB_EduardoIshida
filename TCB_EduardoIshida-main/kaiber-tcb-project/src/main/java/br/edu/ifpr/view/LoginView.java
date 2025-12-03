package br.edu.ifpr.view;

import br.edu.ifpr.controller.UsuarioController;
import br.edu.ifpr.model.User;
import java.util.Scanner;

public class LoginView {
    private Scanner LER;
    private UsuarioController usuarioCtrl;
    private User usuarioLogado;

    public LoginView() {
        this.LER = new Scanner(System.in);
        this.usuarioCtrl = new UsuarioController();
    }

    public User getUsuarioLogado() {
        return usuarioLogado;
    }

    public boolean exibirMenuLogin() {
        System.out.println("=== SISTEMA KYBER - GERENCIAMENTO GEOLÓGICO ===");

        while (usuarioLogado == null) {
            System.out.println("=== LOGIN / REGISTRO ===");
            System.out.println("1 - Fazer login (usuário existente)");
            System.out.println("2 - Registrar novo usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = LER.nextInt();
            LER.nextLine();

            switch (opcao) {
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    registrarUsuario();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    return false;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        return true;
    }

    private void fazerLogin() {
        System.out.println("=== LOGIN ===");
        System.out.println("======Lista de Usuários======");
        usuarioCtrl.listarUsers();
        System.out.println("=============================");
        System.out.print("Digite o ID do seu usuário: ");
        int userId = LER.nextInt();
        LER.nextLine();

        usuarioLogado = usuarioCtrl.selecionarPorID(userId);

        if (usuarioLogado != null) {
            System.out.println("Login realizado com sucesso!");
        } else {
            System.out.println("Usuário não encontrado com ID: " + userId);
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

        usuarioCtrl.cadastrarUser(user);

        if (user.getIduser() > 0) {
            usuarioLogado = user;
            System.out.println("Registro realizado com sucesso! ID: " + user.getIduser());
        } else {
            System.out.println("Erro ao registrar usuário!");
        }
    }
}