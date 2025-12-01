package br.edu.ifpr.model.dao;

import br.edu.ifpr.model.Site;
import java.sql.*;

public class SiteDAO {
    private static Connection conn;

    public SiteDAO(Connection conn) {
        this.conn = conn;
    }
public void inserir(Site site) throws SQLException {
    String sql = "INSERT INTO site (nome, cidade, estado,pais, propriedadeprivada) VALUES (?, ?, ?,?, ?)";
   
    try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, site.getNome());
        stmt.setString(2, site.getCidade());
        stmt.setString(3, site.getEstado());
        stmt.setString(4, site.getPais());
        stmt.setBoolean(5, site.getPropriedadeprivada());
        stmt.executeBatch();

         // Obter o ID gerado

        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                site.setIdsite(generatedKeys.getInt(1)); 
            }
        }
    }
    }
    public static void Selecionar () throws SQLException {
    String sql = "SELECT * FROM site";  
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idsite = rs.getInt("idsite");
                String nome = rs.getString("nome");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String pais = rs.getString("pais");
                boolean propriedadeprivada = rs.getBoolean("propriedadeprivada");

                Site site = new Site(idsite, nome, cidade, estado, pais, propriedadeprivada);
                
            }
        }}
    }
    public static Site selecionarPorID(int x) throws SQLException {
    Site sitePuxado= new Site();
    String sql = "SELECT * FROM site WHERE idsite = ?"; 
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(x, x); 
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int idsite = rs.getInt("idsite");
                String nome = rs.getString("nome");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String pais = rs.getString("pais");
                boolean propriedadeprivada = rs.getBoolean("propriedadeprivada");

                Site site = new Site(idsite, nome, cidade, estado, pais, propriedadeprivada);
                sitePuxado = site;
            }
        }
    }
        return sitePuxado;
}
}