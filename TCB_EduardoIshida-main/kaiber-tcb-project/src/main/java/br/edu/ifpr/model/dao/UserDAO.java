import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ifpr.model.User;

public static void cadastrarUser(User user) throws SQLException {
    String sqlUser = "INSERT INTO user(nome, instituicao, cargo) VALUES(?,?,?)";

    try {
        PreparedStatement psUser = con.prepareStatement(sqlUser);
        psUser.setString(1, user.getName());
        psUser.setString(2, user.getInstitution());
        psUser.setString(3, user.getRole());
        psUser.executeUpdate();
        System.out.println("----BEM VINDO----");

        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                site.setIdsite(generatedKeys.getInt(1));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}