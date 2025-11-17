

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
   private static Connection conexao;

   private ConnectionFactory() {
   }

   public static Connection getConnection() {
      try {
         if (conexao == null) {
            String url = "jdbc:mysql://localhost:3306/Kyber";
            String user = "root";
            String password = "root";
            conexao = DriverManager.getConnection(url, user, password);
            System.out.println("conectado ao banco com sucesso!");
         }
      } catch (SQLException var3) {
         var3.printStackTrace();
      }

      return conexao;
   }
}
