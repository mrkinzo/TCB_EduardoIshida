package br.edu.ifpr.model.dao;

import br.edu.ifpr.model.User;
import java.sql.*;

public class UserDAO {
    private Connection conn;

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

            // Obter o ID gerado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setIduser(generatedKeys.getInt(1));
                }
            }
        }
    }

    // SQL query
    public User buscarPorCredenciais(String nome, String instituicao) throws SQLException {
        String sql = "SELECT * FROM user WHERE nome = ? AND instituicao = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, instituicao);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setIduser(rs.getInt("iduser"));
                    user.setName(rs.getString("nome"));
                    user.setInstitution(rs.getString("instituicao"));
                    user.setRole(rs.getString("cargo"));
                    return user;
                }
                return null;
            }
        }
    }

    public User buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM user WHERE iduser = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setIduser(rs.getInt("iduser"));
                    user.setName(rs.getString("nome"));
                    user.setInstitution(rs.getString("instituicao"));
                    user.setRole(rs.getString("cargo"));
                    return user;
                }
                return null;
            }
        }
    }
}