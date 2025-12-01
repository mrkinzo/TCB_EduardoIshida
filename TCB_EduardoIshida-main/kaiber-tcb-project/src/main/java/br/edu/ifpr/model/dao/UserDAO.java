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
    
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setIduser(generatedKeys.getInt(1));
                }
            }
        }
    }

}