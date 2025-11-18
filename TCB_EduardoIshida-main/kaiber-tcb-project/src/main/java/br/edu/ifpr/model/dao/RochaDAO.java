package br.edu.ifpr.model.dao;

import java.sql.Connection; // Import the corrected Rocha class
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.model.Rocha;

public class RochaDAO {

    private final Connection conn;

    public RochaDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Rocha r) throws SQLException {
        // SQL updated: 'ignea' replaced with 'gem'
        String sql = "INSERT INTO rochas (tipo, dureza, corPrincipal, gem, site_idsite) "
                   + "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, r.getTipo());
        stmt.setString(2, r.getDureza());
        stmt.setString(3, r.getCorPrincipal());
        // Method updated: r.isIgnea() replaced with r.isGem()
        stmt.setBoolean(4, r.isGem());
        stmt.setInt(5, r.getSiteIdSite());
        stmt.executeUpdate();
    }

    public Rocha buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM rochas WHERE idrochas=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Rocha r = new Rocha(
                rs.getInt("idrochas"),
                rs.getString("tipo"),
                rs.getString("dureza"),
                rs.getString("corPrincipal"),
                // Column name updated: "ignea" replaced with "gem"
                rs.getBoolean("gem"),
                rs.getInt("site_idsite")
            );
            return r;
        }
        return null;
    }

    public List<Rocha> listarTodos() throws SQLException {
        String sql = "SELECT * FROM rochas";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Rocha> lista = new ArrayList<>();

        while (rs.next()) {
            lista.add(new Rocha(
                rs.getInt("idrochas"),
                rs.getString("tipo"),
                rs.getString("dureza"),
                rs.getString("corPrincipal"),
                // Column name updated: "ignea" replaced with "gem"
                rs.getBoolean("gem"),
                rs.getInt("site_idsite")
            ));
        }
        return lista;
    }

    public void atualizar(Rocha r) throws SQLException {
 
        String sql = "UPDATE rochas SET tipo=?, dureza=?, corPrincipal=?, gem=?, site_idsite=? "
                   + "WHERE idrochas=?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, r.getTipo());
        stmt.setString(2, r.getDureza());
        stmt.setString(3, r.getCorPrincipal());
        stmt.setBoolean(4, r.isGem());
        stmt.setInt(5, r.getSiteIdSite());
        stmt.setInt(6, r.getIdRochas());
        stmt.executeUpdate();
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM rochas WHERE idrochas=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}