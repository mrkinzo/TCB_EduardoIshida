import br.edu.ifpr.agenda.model.Contato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
  public UserDAO() {

  }

  public static void cadastrarUser(User user) {
    String sqlUser = "INSERT INTO user(nome, insituicao,cargo) VALUES(?,?,?)";
    Connection con = ConnectionFactory.getConnection();

    try{ PreparedStatement psUser = con.prepareStatement(sqlUser);
    psUser.setString(1, user.getName());
    psUser.setString(2, user.getInstitution());
    psUser.setString(3, user.getRole());
    psUser.executeUpdate();
    System.out.println("----BEM VINDO----");
 }
 }
}
