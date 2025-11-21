package br.edu.ifpr.main;

import br.edu.ifpr.controller.EmprestimoController;
import br.edu.ifpr.controller.MineralController;
import br.edu.ifpr.controller.RochaController;
import br.edu.ifpr.model.Emprestimo;
import br.edu.ifpr.model.Mineral;
import br.edu.ifpr.model.Rocha;
import br.edu.ifpr.model.Site;
import br.edu.ifpr.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner LER = new Scanner(System.in);
    private static MineralController mineralCtrl = new MineralController();
    private static RochaController rochaCtrl = new RochaController();
    private static UsuarioController usuarioCtrl = new UsuarioController();
    private static EmprestimoController emprestimoCtrl = new EmprestimoController();

    public static void main(String[] args) {
        User user = new User();
        cadastroDeUsuario(user);

        System.out.println("----====seja bem vindo " + user.getName() + " ----====");

        System.out.println("Por favor, selecione uma opção()");
        int x = LER.nextInt();
        switch (x) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            default:
                break;
        }
    }

    public static void cadastroDeUsuario(User user) {
        user.setName(LER.next());
        user.setInstitution(LER.next());
        user.setRole(LER.next());
        us.cadastrarUser(user);
    }

    public static void cadastrarMineral() {
        
    }

    public static void cadastrarRocha() {

        System.out.println("Cadastrando nova rocha:");
        System.out.println("insire os dados da rocha: tipo, dureza, cor principal, é gema(true/false) e dados do site (nome, cidade, país, visitável(true/false))");
        Rocha rocha = new Rocha();
        rocha.setNa
        rocha.setTipo(LER.next());
        rocha.setDureza(LER.next());
        rocha.setCorPrincipal(LER.next());
        rocha.setGem(LER.nextBoolean());
        Site site = new Site();
        cadastrarSite(site);
        rocha.setSite(site);
        rochaCtrl.cadastrarRocha(rocha);
    }

    public static void cadastrarSite(Site site) {
        site.setName(LER.next());
        site.setCity(LER.next());
        site.setCountry(LER.next());
        site.setVisitable(LER.nextBoolean());
        siteCtrl.cadastrarSite(site);
    }
}