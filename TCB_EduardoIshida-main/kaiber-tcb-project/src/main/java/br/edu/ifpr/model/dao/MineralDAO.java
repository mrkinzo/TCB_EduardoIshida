package br.edu.ifpr.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.model.Mineral;

public class MineralDAO {

    private final Connection conn;

    public MineralDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Mineral m) throws SQLException {
        String sql = "INSERT INTO minerais (tipo, dureza, cor, brilho, toxicidade, site_idsite) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, m.getTipo());
        stmt.setFloat(2, m.getDureza());
        stmt.setString(3, m.getCor());
        stmt.setString(4, m.getBrilho());
        stmt.setString(5, m.getToxicidade());
        stmt.setInt(6, m.getSiteIdSite());
        stmt.executeUpdate();
    }

    public Mineral buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM minerais WHERE idminerais = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Mineral m = new Mineral(
                    rs.getInt("idminerais"),
                    rs.getString("tipo"),
                    rs.getFloat("dureza"),
                    rs.getString("cor"),
                    rs.getString("brilho"),
                    rs.getString("toxicidade"),
                    rs.getInt("site_idsite"));
            return m;
        }
        return null;
    }

    public List<Mineral> listarTodos() throws SQLException {
        String sql = "SELECT * FROM minerais";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Mineral> lista = new ArrayList<>();

        while (rs.next()) {
            lista.add(new Mineral(
                    rs.getInt("idminerais"),
                    rs.getString("tipo"),
                    rs.getFloat("dureza"),
                    rs.getString("cor"),
                    rs.getString("brilho"),
                    rs.getString("toxicidade"),
                    rs.getInt("site_idsite")));
        }
        return lista;
    }

    public void atualizar(Mineral m) throws SQLException {
        String sql = "UPDATE minerais SET tipo=?, dureza=?, cor=?, brilho=?, toxicidade=?, site_idsite=? "
                + "WHERE idminerais=?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, m.getTipo());
        stmt.setFloat(2, m.getDureza());
        stmt.setString(3, m.getCor());
        stmt.setString(4, m.getBrilho());
        stmt.setString(5, m.getToxicidade());
        stmt.setInt(6, m.getSiteIdSite());
        stmt.executeUpdate();
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM minerais WHERE idminerais=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
