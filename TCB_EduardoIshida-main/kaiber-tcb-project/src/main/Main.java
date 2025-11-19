package br.edu.ifpr.main;

import br.edu.ifpr.controller.EmprestimoController;
import br.edu.ifpr.controller.MineralController;
import br.edu.ifpr.controller.RochaController;
import br.edu.ifpr.model.Emprestimo;
import br.edu.ifpr.model.Mineral;
import br.edu.ifpr.model.Rocha;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Início do Simulação de Empréstimo ---");

        // Assumimos que a ConnectionFactory.java está configurada e funcionando.
        MineralController mineralCtrl = new MineralController();
        RochaController rochaCtrl = new RochaController();
        EmprestimoController emprestimoCtrl = new EmprestimoController();

        try {
            // --- 1. PREPARAÇÃO DE PRÉ-REQUISITOS (Necessário no BD) ---
            // Você precisa garantir que o site e o usuário existam antes de rodar.
            final int ID_SITE_EXISTENTE = 1; 
            final int ID_USER_EXISTENTE = 1; 

            // --- 2. CADASTRO DE ITENS (Obtenção dos IDs AI) ---
            System.out.println("\n[SETUP] 1. Cadastrando itens para obter IDs...");
            
            // Criação dos objetos sem o ID
            Mineral m1 = new Mineral("Quartzo", 7.0f, "Incolor", "Vítreo", "Não", ID_SITE_EXISTENTE);
            Rocha r1 = new Rocha("Granito", "Muito Dura", "Cinza", false, ID_SITE_EXISTENTE);

            // O Controller chama o DAO, que insere no BD e ATUALIZA o ID no objeto m1/r1.
            mineralCtrl.cadastrarMineral(m1); 
            rochaCtrl.cadastrarRocha(r1);     
            
            // Agora, os IDs AI estão disponíveis nos objetos:
            System.out.println("-> Mineral inserido com ID (AI): " + m1.getIdminerais()); 
            System.out.println("-> Rocha inserida com ID (AI): " + r1.getIdRochas());

            // --- 3. EXECUÇÃO DO FLUXO DE EMPRÉSTIMO ---

            // A. Listas de Itens com seus IDs
            List<Mineral> mineraisEmprestados = new ArrayList<>();
            mineraisEmprestados.add(m1);
            
            List<Rocha> rochasEmprestadas = new ArrayList<>();
            rochasEmprestadas.add(r1);

            // B. Criação do Objeto Empréstimo
            Emprestimo novoEmprestimo = new Emprestimo(
                LocalDate.now(),                 
                LocalDate.now().plusWeeks(3),    
                ID_USER_EXISTENTE,               
                mineraisEmprestados,             
                rochasEmprestadas                
            );

            // C. Registro do Empréstimo (Transação)
            System.out.println("\n[FLUXO] 2. Registrando Emprestimo (Transação BD)...");
            emprestimoCtrl.cadastrarEmprestimo(novoEmprestimo);
            
            System.out.println("\n[SUCESSO] Empréstimo registrado. Verifique as tabelas user_has_emprestimo, emprestimo_has_minerais e emprestimo_has_Rochas no BD.");

        } catch (Exception e) {
            System.err.println("\nERRO CRÍTICO NA APLICAÇÃO:");
            e.printStackTrace();
        }
    }
}