package br.edu.ifpr.model.dao;

import br.edu.ifpr.model.Site;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiteDAO {

    private final Connection conn;   // não deve ser static

    public SiteDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Site site) throws SQLException {
        String sql = "INSERT INTO site (nome, cidade, estado, pais, propriedadeprivada) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, site.getNome());
            stmt.setString(2, site.getCidade());
            stmt.setString(3, site.getEstado());
            stmt.setString(4, site.getPais());
            stmt.setBoolean(5, site.getPropriedadeprivada());

            stmt.executeUpdate();   // CORRETO

            // Obtém ID gerado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    site.setIdsite(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Site> selecionarTodos() throws SQLException {
        List<Site> lista = new ArrayList<>();

        String sql = "SELECT * FROM site";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idsite = rs.getInt("idsite");
                String nome = rs.getString("nome");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String pais = rs.getString("pais");
                boolean propriedadeprivada = rs.getBoolean("propriedadeprivada");

                lista.add(new Site(idsite, nome, cidade, estado, pais, propriedadeprivada));
            }
        }
        return lista;
    }

    public Site selecionarPorID(int id) throws SQLException {
        Site site = null;

        String sql = "SELECT * FROM site WHERE idsite = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);   // CORRIGIDO

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    site = new Site(
                            rs.getInt("idsite"),
                            rs.getString("nome"),
                            rs.getString("cidade"),
                            rs.getString("estado"),
                            rs.getString("pais"),
                            rs.getBoolean("propriedadeprivada")
                    );
                }
            }
        }

        return site;
    }
}
