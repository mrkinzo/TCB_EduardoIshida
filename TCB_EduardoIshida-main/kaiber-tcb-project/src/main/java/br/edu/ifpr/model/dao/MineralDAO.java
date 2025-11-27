package br.edu.ifpr.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifpr.model.Mineral;

public class MineralDAO {
    private Connection conn;

    public MineralDAO(Connection conn) {
        this.conn = conn;
    }

    // ✅ CORRIGIDO: Método de inserção
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
            stmt.setString(7, String.valueOf(mineral.getSiteIdSite())); // site_idsite é VARCHAR
            stmt.executeUpdate();

            // Obter o ID gerado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    mineral.setIdminerais(generatedKeys.getInt(1));
                }
            }
        }
    }

    // ✅ CORRIGIDO: Buscar mineral por ID
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

    // ✅ CORRIGIDO: Listar todos os minerais
    public List<Mineral> listarTodos() throws SQLException {
        String sql = "SELECT * FROM minerais";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            List<Mineral> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(criarMineralFromResultSet(rs));
            }
            return lista;
        }
    }

    // ✅ CORRIGIDO: Método para atualizar mineral
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
            stmt.setString(7, String.valueOf(mineral.getSiteIdSite()));
            stmt.setInt(8, mineral.getIdminerais());
            stmt.executeUpdate();
        }
    }

    // ✅ CORRIGIDO: Método para deletar mineral
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM minerais WHERE idminerais = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // ✅ CORRIGIDO: Buscar minerais por tipo
    public List<Mineral> buscarPorTipo(String tipo) throws SQLException {
        String sql = "SELECT * FROM minerais WHERE tipo = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo);

            try (ResultSet rs = stmt.executeQuery()) {
                List<Mineral> lista = new ArrayList<>();
                while (rs.next()) {
                    lista.add(criarMineralFromResultSet(rs));
                }
                return lista;
            }
        }
    }

    // ✅ CORRIGIDO: Contar total de minerais
    public int contarTotal() throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM minerais";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
            return 0;
        }
    }

    // ✅ CORRIGIDO: Método auxiliar privado para criar Mineral do ResultSet
    private Mineral criarMineralFromResultSet(ResultSet rs) throws SQLException {
        Mineral mineral = new Mineral(0, null, 0, null, null, null, null);
        mineral.setIdminerais(rs.getInt("idminerais"));
        mineral.setNome(rs.getString("nome"));
        mineral.setTipo(rs.getString("tipo"));
        mineral.setDureza(rs.getFloat("dureza"));
        mineral.setCor(rs.getString("cor"));
        mineral.setBrilho(rs.getString("brilho"));
        mineral.setToxicidade(rs.getString("toxicidade"));
        mineral.setSiteIdSite(rs.getInt("site_idsite"));
        
        return mineral;
    }
}