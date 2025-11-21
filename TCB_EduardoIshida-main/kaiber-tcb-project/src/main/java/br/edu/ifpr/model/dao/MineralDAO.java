import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ifpr.model.Mineral;

public void inserir(Mineral m) throws SQLException {
    String sql = "INSERT INTO minerais (tipo, dureza, cor, brilho, toxicidade, site_idsite) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    stmt.setString(1, m.getTipo());
    stmt.setFloat(2, m.getDureza());
    stmt.setString(3, m.getCor());
    stmt.setString(4, m.getBrilho());
    stmt.setString(5, m.getToxicidade());
    stmt.setInt(6, m.getSiteIdSite());
    stmt.executeUpdate();

    // Obter o ID gerado
    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
            m.setIdminerais(generatedKeys.getInt(1));
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
            rs.getInt("site_idsite")
        ));
    }
    return lista;
}       
}