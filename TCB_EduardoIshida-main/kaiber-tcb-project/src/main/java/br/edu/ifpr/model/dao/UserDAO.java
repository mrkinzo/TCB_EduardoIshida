package br.edu.ifpr.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.edu.ifpr.model.User;
public class UserDAO {
    private static Connection conn;  

    public UserDAO(Connection connection) {
        conn = connection;
    }
public static void cadastrarUser(User user) throws SQLException { 
    String sqlUser = "INSERT INTO user(nome, instituicao, cargo) VALUES(?,?,?)"; 

    try{ 
        PreparedStatement psUser = conn.prepareStatement(sqlUser);
        psUser.setString(1, user.getName());
        psUser.setString(2, user.getInstitution());
        psUser.setString(3, user.getRole());
        psUser.executeUpdate();
    } catch (SQLException e) { 
        e.printStackTrace();
    } finally {
        if (conn!= null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
}