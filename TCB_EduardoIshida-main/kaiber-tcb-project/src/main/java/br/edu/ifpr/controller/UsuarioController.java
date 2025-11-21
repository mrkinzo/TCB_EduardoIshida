package br.edu.ifpr.controller;

import br.edu.ifpr.model.User;
import br.edu.ifpr.model.dao.UserDAO;
import java.sql.SQLException;

public class UsuarioController {

    public void cadastrarUser(User user) {
        try {
            UserDAO.cadastrarUser(user);
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }
}