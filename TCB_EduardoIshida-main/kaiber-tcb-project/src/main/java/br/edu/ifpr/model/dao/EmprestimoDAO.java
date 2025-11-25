package br.edu.ifpr.model.dao;
import java.sql.*;
import java.util.List;
import br.edu.ifpr.model.Mineral;
import br.edu.ifpr.model.Rocha;

public class EmprestimoDAO {
    private Connection conn;

    public EmprestimoDAO(Connection conn) {
        this.conn = conn;
    }

    public int realizarEmprestimo(
            int userId,
            List<Mineral> minerais,
            List<Rocha> rochas
    ) throws SQLException {

        String sqlEmprestimo = 
            "INSERT INTO emprestimo (dataEmp, dataDev, user_iduser) " +
            "VALUES (CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY), ?)";

        String sqlMinerais = 
            "INSERT INTO emprestimo_has_minerais " +
            "(emprestimo_idemprestimo, emprestimo_user_iduser, minerais_idminerais, minerais_site_idsite) " +
            "VALUES (?, ?, ?, ?)";

        String sqlRochas = 
            "INSERT INTO emprestimo_has_Rochas " +
            "(emprestimo_idemprestimo, emprestimo_user_iduser, Rochas_idRochas, Rochas_site_idsite) " +
            "VALUES (?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false);

            // 1 — Criar empréstimo
            int emprestimoId;
            try (PreparedStatement stmt = conn.prepareStatement(sqlEmprestimo, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    rs.next();
                    emprestimoId = rs.getInt(1);
                }
            }

            // 2 — Inserir minerais
            try (PreparedStatement stmt = conn.prepareStatement(sqlMinerais)) {
                for (Mineral m : minerais) {
                    stmt.setInt(1, emprestimoId);
                    stmt.setInt(2, userId);
                    stmt.setInt(3, m.getIdminerais());
                    stmt.setInt(4, m.getSite().getIdsite());
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }

            // 3 — Inserir rochas
            try (PreparedStatement stmt = conn.prepareStatement(sqlRochas)) {
                for (Rocha r : rochas) {
                    stmt.setInt(1, emprestimoId);
                    stmt.setInt(2, userId);
                    stmt.setInt(3, r.getIdRochas());
                    stmt.setInt(4, r.getSiteId());
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }

            conn.commit();
            return emprestimoId;

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}