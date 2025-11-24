import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ifpr.model.Mineral;
import br.edu.ifpr.model.Rocha;

public void inserir(Mineral m) throws SQLException {
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