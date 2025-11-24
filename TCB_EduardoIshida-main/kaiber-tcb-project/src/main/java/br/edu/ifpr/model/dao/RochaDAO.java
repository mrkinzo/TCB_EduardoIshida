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

    // INSERIR - Corrigido para o schema real
    public void inserir(Rocha rocha) throws SQLException {
        String sql = "INSERT INTO Rochas (tipo, dureza, corPrincipal, composicaoPrincipal, isitgem, " +
                "site_idsite, site_nome, site_cidade, site_pais, site_propriedadeprivada) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, rocha.getTipo());
            stmt.setString(2, rocha.getDureza());
            stmt.setString(3, rocha.getCorPrincipal());
            stmt.setString(4, rocha.getComposicaoPrincipal());
            stmt.setBoolean(5, rocha.isGem());
            stmt.setInt(6, rocha.getSite().getsId());
            stmt.setString(7, rocha.getSite().getNome());
            stmt.setString(8, rocha.getSite().getCidade());
            stmt.setString(9, rocha.getSite().getPais());
            stmt.setString(10, rocha.getSite().getPropriedadePrivada());
            stmt.executeUpdate();

            // Obter o ID gerado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    rocha.setIdRochas(generatedKeys.getInt(1));
                }
            }
        }
    }

    // BUSCAR POR ID - Corrigido para o schema real (sem JOIN necessário)
    public Rocha buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Rochas WHERE idRochas = ?";

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

    // BUSCAR GEMAS
    public List<Rocha> buscarGemas() throws SQLException {
        String sql = "SELECT * FROM Rochas WHERE isitgem = true ORDER BY tipo";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            return criarListaRochasFromResultSet(rs);
        }
    }

    // LISTAR TODAS - Corrigido para o schema real (sem JOIN necessário)
    public List<Rocha> listarTodas() throws SQLException {
        String sql = "SELECT * FROM Rochas ORDER BY tipo";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            return criarListaRochasFromResultSet(rs);
        }
    }

    // ATUALIZAR - Corrigido para o schema real
    public void atualizar(Rocha rocha) throws SQLException {
        String sql = "UPDATE Rochas SET tipo = ?, dureza = ?, corPrincipal = ?, composicaoPrincipal = ?, " +
                "isitgem = ?, site_idsite = ?, site_nome = ?, site_cidade = ?, site_pais = ?, " +
                "site_propriedadeprivada = ? WHERE idRochas = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rocha.getTipo());
            stmt.setString(2, rocha.getDureza());
            stmt.setString(3, rocha.getCorPrincipal());
            stmt.setString(4, rocha.getComposicaoPrincipal());
            stmt.setBoolean(5, rocha.isGem());
            stmt.setInt(6, rocha.getSite().getsId());
            stmt.setString(7, rocha.getSite().getNome());
            stmt.setString(8, rocha.getSite().getCidade());
            stmt.setString(9, rocha.getSite().getPais());
            stmt.setString(10, rocha.getSite().getPropriedadePrivada());
            stmt.setInt(11, rocha.getIdRochas());
            stmt.executeUpdate();
        }
    }

    // DELETAR - Corrigido para o schema real
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Rochas WHERE idRochas = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // MÉTODOS AUXILIARES PRIVADOS
    private Rocha criarRochaFromResultSet(ResultSet rs) throws SQLException {
        // Criar objeto Site com dados da própria tabela Rochas
        Site site = new Site(
                rs.getString("site_nome"),
                rs.getInt("site_idsite"),
                rs.getString("site_pais"),
                rs.getString("site_cidade"),
                rs.getString("site_propriedadeprivada").equalsIgnoreCase("true"),
                false // visitavel - ajuste conforme necessário
        );

        // Criar e retornar Rocha
        return new Rocha(
                rs.getInt("idRochas"),
                rs.getString("tipo"),
                rs.getString("dureza"),
                rs.getString("corPrincipal"),
                rs.getString("composicaoPrincipal"),
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