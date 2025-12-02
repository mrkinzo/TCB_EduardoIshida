package br.edu.ifpr.controller;

import br.edu.ifpr.model.Emprestimo;
import br.edu.ifpr.model.Mineral;
import br.edu.ifpr.model.Rocha;
import br.edu.ifpr.model.dao.EmprestimoDAO;
import br.edu.ifpr.model.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.edu.ifpr.*;

public class EmprestimoController {
    private EmprestimoDAO emprestimoDAO;
    private Connection conn;

    public EmprestimoController() {
        this.conn = ConnectionFactory.getConnection();
        this.emprestimoDAO = new EmprestimoDAO(conn);
    }

    public int realizarEmprestimo(int userId, List<Mineral> minerais, List<Rocha> rochas) {
        try {
            return emprestimoDAO.realizarEmprestimo(userId, minerais, rochas);
        } catch (SQLException e) {
            System.err.println("Erro ao realizar empréstimo: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public List<Emprestimo> buscarEmprestimosPorUsuario(int usuarioId) {
        try {
            List<Emprestimo> todos = emprestimoDAO.listarTodos();
            if (todos != null) {
                todos.removeIf(emp -> emp.getUsuarioId() != usuarioId);
            }
            return todos;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar empréstimos: " + e.getMessage());
            return null;
        }
    }

    public void devolverEmprestimo(int emprestimoId) {
        try {
            emprestimoDAO.devolverEmprestimo(emprestimoId);
            System.out.println("Empréstimo #" + emprestimoId + " devolvido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao devolver empréstimo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}