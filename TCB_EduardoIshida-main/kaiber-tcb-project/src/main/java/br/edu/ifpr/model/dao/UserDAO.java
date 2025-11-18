package br.edu.ifpr.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.ifpr.model.User;

public class UserDAO {
  
  
  public UserDAO() {

  }

  public static void cadastrarUser(User user) throws SQLException { 
    String sqlUser = "INSERT INTO user(nome, insituicao,cargo) VALUES(?,?,?)";
   
    Connection con = ConnectionFactory.getConnection(); 

    try{ 
        PreparedStatement psUser = con.prepareStatement(sqlUser);
        psUser.setString(1, user.getName());
        psUser.setString(2, user.getInstitution());
        psUser.setString(3, user.getRole());
        psUser.executeUpdate();
        System.out.println("----BEM VINDO----");
    } catch (SQLException e) { 
        e.printStackTrace();
    }
   
    finally {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
  }
}