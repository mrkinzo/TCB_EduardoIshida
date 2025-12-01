package br.edu.ifpr.controller;

import br.edu.ifpr.model.User;
import br.edu.ifpr.model.dao.UserDAO;
import br.edu.ifpr.model.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class UsuarioController {
    private UserDAO userDAO;
    private Connection conn;

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

    public  void listarUsers() {
        try {
            for (User user : UserDAO.listarUsers()) {
                System.out.println("ID: " + user.getIduser() + ", Nome: " + user.getName() +
                        ", Instituição: " + user.getInstitution() + ", Cargo: " + user.getRole());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public User selecionarPorID(int userId) {
        try {
            return UserDAO.selecionarPorID(userId);
        } catch (SQLException e) {
            System.err.println("Erro ao selecionar usuário por ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}