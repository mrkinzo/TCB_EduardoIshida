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
        String sql = "INSERT INTO rochas (tipo, dureza, corPrincipal, gem, site_idsite) VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, rocha.getTipo());
        stmt.setString(2, rocha.getDureza());
        stmt.setString(3, rocha.getCorPrincipal());
        stmt.setBoolean(4, rocha.isGem());
        stmt.setInt(5, rocha.getSite().getsId());
        stmt.executeUpdate();

        // Obter o ID gerado
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                rocha.setIdRochas(generatedKeys.getInt(1));
            }
        }
    }

    // BUSCAR POR ID (com JOIN para trazer dados do Site)
    public Rocha buscarPorId(int id) throws SQLException {
        String sql = "SELECT r.*, s.nome as site_nome, s.cidade as site_cidade, s.pais as site_pais, s.visitavel as site_visitavel " +
                     "FROM rochas r " +
                     "INNER JOIN site s ON r.site_idsite = s.idsite " +
                     "WHERE r.idrochas = ?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return criarRochaFromResultSet(rs);
        }
        return null;
    }

    // BUSCAR POR TIPO
    public List<Rocha> buscarPorTipo(String tipo) throws SQLException {
        String sql = "SELECT r.*, s.nome as site_nome, s.cidade as site_cidade, s.pais as site_pais, s.visitavel as site_visitavel " +
                     "FROM rochas r " +
                     "INNER JOIN site s ON r.site_idsite = s.idsite " +
                     "WHERE r.tipo LIKE ? " +
                     "ORDER BY r.tipo";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + tipo + "%");

        ResultSet rs = stmt.executeQuery();
        return criarListaRochasFromResultSet(rs);
    }

    // BUSCAR POR SITE
    public List<Rocha> buscarPorSite(int siteId) throws SQLException {
        String sql = "SELECT r.*, s.nome as site_nome, s.cidade as site_cidade, s.pais as site_pais, s.visitavel as site_visitavel " +
                     "FROM rochas r " +
                     "INNER JOIN site s ON r.site_idsite = s.idsite " +
                     "WHERE r.site_idsite = ? " +
                     "ORDER BY r.tipo";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, siteId);

        ResultSet rs = stmt.executeQuery();
        return criarListaRochasFromResultSet(rs);
    }

    // BUSCAR GEMAS
    public List<Rocha> buscarGemas() throws SQLException {
        String sql = "SELECT r.*, s.nome as site_nome, s.cidade as site_cidade, s.pais as site_pais, s.visitavel as site_visitavel " +
                     "FROM rochas r " +
                     "INNER JOIN site s ON r.site_idsite = s.idsite " +
                     "WHERE r.gem = true " +
                     "ORDER BY r.tipo";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return criarListaRochasFromResultSet(rs);
    }

    // LISTAR TODAS (com dados do Site)
    public List<Rocha> listarTodas() throws SQLException {
        String sql = "SELECT r.*, s.nome as site_nome, s.cidade as site_cidade, s.pais as site_pais, s.visitavel as site_visitavel " +
                     "FROM rochas r " +
                     "INNER JOIN site s ON r.site_idsite = s.idsite " +
                     "ORDER BY r.tipo";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return criarListaRochasFromResultSet(rs);
    }

    // LISTAR POR DUREZA
    public List<Rocha> listarPorDureza(String dureza) throws SQLException {
        String sql = "SELECT r.*, s.nome as site_nome, s.cidade as site_cidade, s.pais as site_pais, s.visitavel as site_visitavel " +
                     "FROM rochas r " +
                     "INNER JOIN site s ON r.site_idsite = s.idsite " +
                     "WHERE r.dureza = ? " +
                     "ORDER BY r.tipo";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, dureza);

        ResultSet rs = stmt.executeQuery();
        return criarListaRochasFromResultSet(rs);
    }

    // ATUALIZAR
    public void atualizar(Rocha rocha) throws SQLException {
        String sql = "UPDATE rochas SET tipo = ?, dureza = ?, corPrincipal = ?, gem = ?, site_idsite = ? WHERE idrochas = ?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, rocha.getTipo());
        stmt.setString(2, rocha.getDureza());
        stmt.setString(3, rocha.getCorPrincipal());
        stmt.setBoolean(4, rocha.isGem());
        stmt.setInt(5, rocha.getSite().getsId());
        stmt.setInt(6, rocha.getIdRochas());
        stmt.executeUpdate();
    }

    // DELETAR
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM rochas WHERE idrochas = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    // CONTAR TOTAL DE ROCHAS
    public int contarTotal() throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM rochas";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            return rs.getInt("total");
        }
        return 0;
    }

    // CONTAR GEMAS
    public int contarGemas() throws SQLException {
        String sql = "SELECT COUNT(*) as total FROM rochas WHERE gem = true";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            return rs.getInt("total");
        }
        return 0;
    }

    // MÉTODOS AUXILIARES PRIVADOS

    private Rocha criarRochaFromResultSet(ResultSet rs) throws SQLException {
        // Criar objeto Site
        Site site = new Site(
            rs.getString("site_nome"),
            rs.getInt("site_idsite"),
            rs.getString("site_pais"),
            rs.getString("site_cidade"),
            false, // publicProperty - ajuste conforme necessário
            rs.getBoolean("site_visitavel")
        );

        // Criar e retornar Rocha
        return new Rocha(
            String.valueOf(rs.getInt("idrochas")),
            rs.getString("tipo"),
            rs.getString("dureza"),
            rs.getString("corPrincipal"),
            rs.getBoolean("gem"),
            site
        );
    }

    private List<Rocha> criarListaRochasFromResultSet(ResultSet rs) throws SQLException {
        List<Rocha> rochas = new ArrayList<>();
        
        while (rs.next()) {
            rochas.add(criarRochaFromResultSet(rs));
        }
        
        return rochas;
    }

    // VERIFICAR SE EXISTE ROCHA COM TIPO
    public boolean existePorTipo(String tipo) throws SQLException {
        String sql = "SELECT COUNT(*) as count FROM rochas WHERE tipo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, tipo);
        
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("count") > 0;
        }
        return false;
    }

    // BUSCAR POR INTERVALO DE IDS
    public List<Rocha> buscarPorIntervaloIds(int idInicial, int idFinal) throws SQLException {
        String sql = "SELECT r.*, s.nome as site_nome, s.cidade as site_cidade, s.pais as site_pais, s.visitavel as site_visitavel " +
                     "FROM rochas r " +
                     "INNER JOIN site s ON r.site_idsite = s.idsite " +
                     "WHERE r.idrochas BETWEEN ? AND ? " +
                     "ORDER BY r.idrochas";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idInicial);
        stmt.setInt(2, idFinal);

        ResultSet rs = stmt.executeQuery();
        return criarListaRochasFromResultSet(rs);
    }
}