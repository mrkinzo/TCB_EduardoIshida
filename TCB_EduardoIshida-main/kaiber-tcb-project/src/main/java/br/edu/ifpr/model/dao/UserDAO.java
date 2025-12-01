package br.edu.ifpr.model.dao;

import br.edu.ifpr.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserDAO {
    private static Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public void cadastrarUser(User user) throws SQLException {
        String sql = "INSERT INTO user (nome, instituicao, cargo) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getInstitution());
            stmt.setString(3, user.getRole());
            stmt.executeUpdate();  
    
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setIduser(generatedKeys.getInt(1));
                }
            }
        }
    }
     
    public static List <User> listarUsers() throws SQLException {
        List<User> lista = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new User(
                    rs.getInt("iduser"),
                    rs.getString("nome"),
                    rs.getString("instituicao"),
                    rs.getString("cargo")
                ));
            }
        }
        return lista;
    }
    public static User selecionarPorID(int id) throws SQLException {
        String sql = "SELECT * FROM user WHERE iduser = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("iduser"),
                        rs.getString("nome"),
                        rs.getString("instituicao"),
                        rs.getString("cargo")
                    );
                }
                return null;
            }
        }
    }
}