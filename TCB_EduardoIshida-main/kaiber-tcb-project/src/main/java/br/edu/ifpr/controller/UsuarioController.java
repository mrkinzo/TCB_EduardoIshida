package br.edu.ifpr.controller;

import br.edu.ifpr.model.User;
import br.edu.ifpr.model.dao.UserDAO;
import br.edu.ifpr.model.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioController {
    private UserDAO userDAO;
    private Connection conn;
    private static Scanner LER = new Scanner(System.in);

    public UsuarioController() {
        this.conn = ConnectionFactory.getConnection();
        this.userDAO = new UserDAO(conn);
    }

    public void cadastrarUser(User user) {
        try {
            userDAO.cadastrarUser(user);
            System.out.println("Usuário cadastrado com sucesso! ID: " + user.getIduser());
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public User buscarPorCredenciais(String nome, String instituicao) {
        try {
            return userDAO.buscarPorCredenciais(nome, instituicao);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
            return null;
        }
    }

    public User buscarPorId(int id) {
        try {
            return userDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por ID: " + e.getMessage());
            return null;
        }
    }
}