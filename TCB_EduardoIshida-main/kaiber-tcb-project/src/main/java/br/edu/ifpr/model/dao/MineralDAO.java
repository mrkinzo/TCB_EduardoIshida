package br.edu.ifpr.model.dao;

import br.edu.ifpr.model.Mineral;
import br.edu.ifpr.model.Site;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MineralDAO {
    private static Connection conn;

    public MineralDAO(Connection conn) {
        this.conn = conn;
    }

    //  INSERIR
    public void inserir(Mineral mineral) throws SQLException {
        String sql = "INSERT INTO minerais (nome, tipo, dureza, cor, brilho, toxicidade, site_idsite) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, mineral.getNome());
            stmt.setString(2, mineral.getTipo());
            stmt.setFloat(3, mineral.getDureza());
            stmt.setString(4, mineral.getCor());
            stmt.setString(5, mineral.getBrilho());
            stmt.setString(6, mineral.getToxicidade());
            stmt.setInt(7, mineral.getSite().getIdsite());
            stmt.executeUpdate();

            // Obter o ID gerado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    mineral.setIdminerais(generatedKeys.getInt(1));
                }
            }
        }
    }


    // LISTAR TODOS
    public List<Mineral> ListarTodos() throws SQLException {
         String sql = "SELECT * FROM minerais ORDER BY nome";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            List<Mineral> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(criarMineralFromResultSet(rs));
            }
            return lista;
        }
    }

    // ATUALIZAR - Atualiza mineral E seu site
public void atualizar(Mineral mineral) throws SQLException {
    String sql = "UPDATE minerais SET nome = ?, tipo = ?, dureza = ?, cor = ?, " +
                 "brilho = ?, toxicidade = ?, site_idsite = ? WHERE idminerais = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, mineral.getNome());
        stmt.setString(2, mineral.getTipo());
        stmt.setFloat(3, mineral.getDureza());
        stmt.setString(4, mineral.getCor());
        stmt.setString(5, mineral.getBrilho());
        stmt.setString(6, mineral.getToxicidade());
        stmt.setInt(7, mineral.getSite().getIdsite());  // ← AQUI ATUALIZA O SITE
        stmt.setInt(8, mineral.getIdminerais());
        
        int linhasAfetadas = stmt.executeUpdate();
        System.out.println("DEBUG: Atualização do mineral. Linhas afetadas: " + linhasAfetadas);
    }
}

    // DELETAR
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM minerais WHERE idminerais = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // MÉTODO AUXILIAR PRIVADO
    private Mineral criarMineralFromResultSet(ResultSet rs) throws SQLException {
        Site site = new Site(0, "", "", "", "", false);
        site.setIdsite(rs.getInt("site_idsite"));

        Mineral mineral = new Mineral(
                rs.getInt("idminerais"),
                rs.getString("nome"),
                rs.getString("tipo"),
                rs.getFloat("dureza"),
                rs.getString("cor"),
                rs.getString("brilho"),
                rs.getString("toxicidade"),
                site);

        return mineral;
    }
    public Mineral buscarPorId(int id) throws SQLException {
        String sql = "SELECT m.*, s.idsite, s.nome as site_nome, s.cidade, s.estado, s.pais, s.propriedadeprivada " +
                     "FROM minerais m " +
                     "LEFT JOIN site s ON m.site_idsite = s.idsite " +
                     "WHERE m.idminerais = ?";
    
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
    
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Criar Site COMPLETO com todos os dados
                    Site site = new Site(
                        rs.getInt("idsite"),
                        rs.getString("site_nome"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"),
                        rs.getBoolean("propriedadeprivada")
                    );
                    
                    // Criar Mineral com Site completo
                    return new Mineral(
                        rs.getInt("idminerais"),
                        rs.getString("nome"),
                        rs.getString("tipo"),
                        rs.getFloat("dureza"),
                        rs.getString("cor"),
                        rs.getString("brilho"),
                        rs.getString("toxicidade"),
                        site
                    );
                }
                return null;
            }
        }
    }
}