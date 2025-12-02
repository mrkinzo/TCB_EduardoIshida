package br.edu.ifpr.model.view;

import br.edu.ifpr.controller.EmprestimoController;
import br.edu.ifpr.controller.MineralController;
import br.edu.ifpr.controller.RochaController;
import br.edu.ifpr.model.*;
import java.util.Scanner;


public class EmprestimoView {
    private Scanner LER = new Scanner(System.in);
    private EmprestimoController emprestimoCtrl = new EmprestimoController();
    private MineralController mineralCtrl = new MineralController();
    private RochaController rochaCtrl = new RochaController();
    
    // Nota: usuarioLogado precisa ser passado ou acessado de outra forma
    // Vou assumir que temos uma forma de obter o usuário atual
    
    public void realizarEmprestimoPorID() {
        System.out.println("\n=== REALIZAR EMPRÉSTIMO ===");
        System.out.println("Nota: Esta funcionalidade precisa do usuário logado");
        System.out.println("Implemente a lógica para obter o usuarioLogado");
        
      
    }
    
    public void listarMeusEmprestimos() {
        System.out.println("\n=== MEUS EMPRÉSTIMOS ===");
        
    }
    
    public void devolverEmprestimo() {
        System.out.println("\n=== DEVOLVER EMPRÉSTIMO ===");
        System.out.print("Digite o ID do empréstimo para devolução: ");
        
        try {
            int emprestimoId = LER.nextInt();
            LER.nextLine();
            
            System.out.print("Confirmar devolução do empréstimo #" + emprestimoId + "? (s/n): ");
            String confirmacao = LER.nextLine();
            
            if (confirmacao.equalsIgnoreCase("s")) {
                emprestimoCtrl.devolverEmprestimo(emprestimoId);
                System.out.println("Empréstimo devolvido com sucesso!");
            } else {
                System.out.println("Devolução cancelada.");
            }
        } catch (Exception e) {
            System.out.println("ID inválido!");
        }
    }
}