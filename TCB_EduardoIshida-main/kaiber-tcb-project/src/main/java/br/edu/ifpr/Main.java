package br.edu.ifpr;

import br.edu.ifpr.view.LoginView;
import br.edu.ifpr.view.ViewPrincipal;
import br.edu.ifpr.model.User;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA KYBER - GERENCIAMENTO GEOLÓGICO ===");

        // 1. Criar view de login
        LoginView loginView = new LoginView();

        // 2. Exibir menu de login/registro
        boolean usuarioAutenticado = loginView.exibirMenuLogin();

        if (!usuarioAutenticado) {
            System.out.println("Programa encerrado.");
            return;
        }

        // 3. Obter usuário logado
        User usuarioLogado = loginView.getUsuarioLogado();

        if (usuarioLogado != null) {
            // 4. Criar ViewPrincipal passando o usuário logado
            ViewPrincipal viewPrincipal = new ViewPrincipal(usuarioLogado);
            viewPrincipal.exibirMenuPrincipal();
        } else {
            System.out.println(" Erro: Nenhum usuário logado.");
        }
    }
}