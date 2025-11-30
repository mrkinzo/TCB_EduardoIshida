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

    // ✅ INSERIR
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

    // BUSCAR POR ID
    public Mineral buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM minerais WHERE idminerais = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarMineralFromResultSet(rs);
                }
                return null;
            }
        }
    }

    // LISTAR TODOS
    public List<Mineral> listarTodos() throws SQLException {
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

    // ATUALIZAR
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
            stmt.setInt(7, mineral.getSite().getIdsite());
            stmt.setInt(8, mineral.getIdminerais());
            stmt.executeUpdate();
        }
    }

    // ✅ DELETAR
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM minerais WHERE idminerais = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // MÉTODO AUXILIAR PRIVADO
    private Mineral criarMineralFromResultSet(ResultSet rs) throws SQLException {
        Site site = new Site(0, "", "", "", "");
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
}