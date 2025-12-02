package br.edu.ifpr.model.dao;

import br.edu.ifpr.model.Rocha;
import br.edu.ifpr.model.Site;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RochaDAO {
    private final Connection conn;

    public RochaDAO(Connection conn) {
        this.conn = conn;
    }

    // INSERIR
    public void inserir(Rocha rocha) throws SQLException {
        String sql = "INSERT INTO Rochas (nome, tipo, dureza, corPrincipal, isitgem, site_idsite) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, rocha.getNome());
            stmt.setString(2, rocha.getTipo());
            stmt.setString(3, rocha.getDureza());
            stmt.setString(4, rocha.getCorPrincipal());
            stmt.setBoolean(5, rocha.isGem());
            stmt.setInt(6, rocha.getSite().getIdsite());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    rocha.setIdRochas(generatedKeys.getInt(1));
                }
            }
        }
    }

    public Rocha buscarPorId(int id) throws SQLException {
        String sql = "SELECT r.*, s.idsite, s.nome as site_nome, s.cidade as site_cidade, " +
                "s.estado as site_estado, s.pais as site_pais, " +
                "s.propriedadeprivada as site_propriedadeprivada " +
                "FROM Rochas r " +
                "INNER JOIN site s ON r.site_idsite = s.idsite " +
                "WHERE r.idRochas = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarRochaFromResultSet(rs);
                }
                return null;
            }
        }
    }

    // LISTAR TODAS
    public List<Rocha> listarTodas() throws SQLException {
        String sql = "SELECT r.*, s.nome as site_nome, s.cidade as site_cidade,s.estado as site_estado ,s.pais as site_pais, s.propriedadeprivada as site_propriedadeprivada "
                +
                "FROM Rochas r " +
                "INNER JOIN site s ON r.site_idsite = s.idsite " +
                "ORDER BY r.tipo";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            return criarListaRochasFromResultSet(rs);
        }
    }

    public void atualizar(Rocha rocha) throws SQLException {
        String sql = "UPDATE Rochas SET nome = ?, tipo = ?, dureza = ?, corPrincipal = ?, isitgem = ?, site_idsite = ? WHERE idRochas = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rocha.getNome());
            stmt.setString(2, rocha.getTipo());
            stmt.setString(3, rocha.getDureza());
            stmt.setString(4, rocha.getCorPrincipal());
            stmt.setBoolean(5, rocha.isGem());
            stmt.setInt(6, rocha.getSite().getIdsite());
            stmt.setInt(7, rocha.getIdRochas());
            stmt.executeUpdate();
        }
    }

    // DELETAR
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Rochas WHERE idRochas = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // MÉTODOS AUXILIARES PRIVADOS
    private Rocha criarRochaFromResultSet(ResultSet rs) throws SQLException {
    // Tente com e sem prefixo "site_"
    String estado;
    try {
        estado = rs.getString("site_estado");
    } catch (SQLException e) {
        // Se não encontrar com prefixo, tenta sem
        estado = rs.getString("estado");
    }
    
    Site site = new Site(
        rs.getInt("site_idsite"),
        rs.getString("site_nome"),
        rs.getString("site_cidade"),
        estado,  // ← Use o valor encontrado
        rs.getString("site_pais"),
        rs.getBoolean("site_propriedadeprivada"));
    
    return new Rocha(
        rs.getInt("idRochas"),
        rs.getString("nome"),
        rs.getString("tipo"),
        rs.getString("dureza"),
        rs.getString("corPrincipal"),
        rs.getBoolean("isitgem"),
        site);
}

    private List<Rocha> criarListaRochasFromResultSet(ResultSet rs) throws SQLException {
        List<Rocha> rochas = new ArrayList<>();

        while (rs.next()) {
            rochas.add(criarRochaFromResultSet(rs));
        }

        return rochas;
    }
}