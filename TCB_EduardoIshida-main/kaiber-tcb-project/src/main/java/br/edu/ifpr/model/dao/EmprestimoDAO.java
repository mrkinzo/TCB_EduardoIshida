package br.edu.ifpr.model.dao;

import br.edu.ifpr.model.Mineral;
import br.edu.ifpr.model.Rocha;
import br.edu.ifpr.model.Emprestimo;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;

public class EmprestimoDAO {
    private Connection conn;

    public EmprestimoDAO(Connection conn) {
        this.conn = conn;
    }

    static int userID;

    public int realizarEmprestimo(int userId, List<Mineral> minerais, List<Rocha> rochas) throws SQLException {
        String sqlEmprestimo = "INSERT INTO emprestimo (dataEmp, dataDev, usuario_iduser) VALUES (CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY), ?)";

        String sqlMinerais = "INSERT INTO emprestimo_has_minerais (emprestimo_idemprestimo, minerais_idminerais) VALUES (?, ?)";

        String sqlRochas = "INSERT INTO emprestimo_has_Rochas (emprestimo_idemprestimo, Rochas_idRochas) VALUES (?, ?)";

        try {
            conn.setAutoCommit(false);
            int emprestimoId;

            // 1 — Criar empréstimo
            try (PreparedStatement stmt = conn.prepareStatement(sqlEmprestimo, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        emprestimoId = rs.getInt(1);
                        System.out.println("DEBUG: Empréstimo criado com ID: " + emprestimoId);
                    } else {
                        throw new SQLException("Falha ao obter ID do empréstimo");
                    }
                }
            }

            // 2 — Inserir minerais (se houver)
            if (minerais != null && !minerais.isEmpty()) {
                System.out.println("DEBUG: Inserindo " + minerais.size() + " minerais no empréstimo");

                try (PreparedStatement stmt = conn.prepareStatement(sqlMinerais)) {
                    for (Mineral m : minerais) {
                        stmt.setInt(1, emprestimoId);
                        stmt.setInt(2, m.getIdminerais());
                        stmt.addBatch();
                    }
                    int[] resultados = stmt.executeBatch();
                    System.out.println("DEBUG: Minerais inseridos: " + resultados.length);
                }
            }

            // 3 — Inserir rochas (se houver)
            if (rochas != null && !rochas.isEmpty()) {
                System.out.println("DEBUG: Inserindo " + rochas.size() + " rochas no empréstimo");

                try (PreparedStatement stmt = conn.prepareStatement(sqlRochas)) {
                    for (Rocha r : rochas) {
                        stmt.setInt(1, emprestimoId);
                        stmt.setInt(2, r.getIdRochas());
                        stmt.addBatch();
                    }
                    int[] resultados = stmt.executeBatch();
                    System.out.println("DEBUG: Rochas inseridas: " + resultados.length);
                }
            }

            // 4 — Commit da transação
            conn.commit();
            System.out.println("DEBUG: Transação commitada com sucesso!");

            return emprestimoId;

        } catch (SQLException e) {
            // Rollback em caso de erro
            try {
                conn.rollback();
                System.err.println("DEBUG: Rollback realizado devido a erro: " + e.getMessage());
            } catch (SQLException rollbackEx) {
                System.err.println("ERRO ao fazer rollback: " + rollbackEx.getMessage());
            }
            throw e; // Re-lança a exceção
        } finally {
            // Restaura auto-commit
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("ERRO ao restaurar auto-commit: " + e.getMessage());
            }
        }
    }

    // Buscar empréstimo por ID
    public Emprestimo buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM emprestimo WHERE idemprestimo = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Emprestimo emp = new Emprestimo();
                    emp.setIdemprestimo(rs.getInt("idemprestimo"));
                    emp.setDataEmp(rs.getString("dataEmp"));
                    emp.setDataDev(rs.getString("dataDev"));
                    emp.setUsuarioId(rs.getInt("usuario_iduser"));
                    return emp;
                }
                return null;
            }
        }
    }

    // Listar todos os empréstimos
    public List<Emprestimo> listarTodos() throws SQLException {
        String sql = "SELECT * FROM emprestimo ORDER BY dataEmp DESC";
        List<Emprestimo> emprestimos = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Emprestimo emp = new Emprestimo();
                emp.setIdemprestimo(rs.getInt("idemprestimo"));
                emp.setDataEmp(rs.getString("dataEmp"));
                emp.setDataDev(rs.getString("dataDev"));
                emp.setUsuarioId(rs.getInt("usuario_iduser"));
                emprestimos.add(emp);
            }
        }
        return emprestimos;
    }

    // NOVO: Devolver empréstimo
    public void devolverEmprestimo(int emprestimoId) throws SQLException {
        String sql = "DELETE FROM emprestimo_has_minerais WHERE emprestimo_idemprestimo = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprestimoId);
            stmt.executeUpdate();
        }

        sql = "DELETE FROM emprestimo_has_Rochas WHERE emprestimo_idemprestimo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprestimoId);
            stmt.executeUpdate();
        }

        sql = "DELETE FROM emprestimo WHERE idemprestimo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprestimoId);
            stmt.executeUpdate();
        }
    }
}