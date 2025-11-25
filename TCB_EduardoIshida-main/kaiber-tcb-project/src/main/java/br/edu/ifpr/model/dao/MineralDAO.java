
package br.edu.ifpr.model.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.edu.ifpr.model.Mineral;
import java.sql.SQLException;
import java.util.List;

public class MineralDAO {
    private Connection conn;

    public MineralDAO(Connection conn) {
        this.conn = conn;
    }

public void inserir(Mineral m) throws SQLException {
   
        String sql = "INSERT INTO ms (tipo, dureza, corPrincipal, composicaoPrincipal, isitgem, " +
                "site_idsite, site_nome, site_cidade, site_pais, site_propriedadeprivada) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, m.getTipo());
            stmt.setFloat(2, m.getDureza());
            stmt.setString(3, m.getCor());
            stmt.setInt(6, m.getSite().getIdsite());
            stmt.setString(7, m.getSite().getNome());
            stmt.setString(8, m.getSite().getCidade());
            stmt.setString(9, m.getSite().getPais());
            stmt.setString(10, m.getSite().getPropriedadeprivada());
            stmt.executeUpdate();

            // Obter o ID gerado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    m.setIdms(generatedKeys.getInt(1));
                }
            }
        
    }
    // Buscar mineral por ID
public Mineral buscarPorId(int id) throws SQLException {
    String sql = "SELECT * FROM minerais WHERE idminerais = ?";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, id);

    ResultSet rs = stmt.executeQuery();
    if (rs.next()) {
        return new Mineral(
            rs.getInt("idminerais"),
            rs.getString("tipo"),
            rs.getFloat("dureza"),
            rs.getString("cor"),
            rs.getString("brilho"),
            rs.getString("toxicidade"),
            rs.getInt("site_idsite")
        );
    }
    return null;
}

// Listar todos os minerais
public List <Mineral> listarTodos() throws SQLException {
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
            rs.getInt("site_idsite")
        ));
    }
    return lista;
}       
}
}