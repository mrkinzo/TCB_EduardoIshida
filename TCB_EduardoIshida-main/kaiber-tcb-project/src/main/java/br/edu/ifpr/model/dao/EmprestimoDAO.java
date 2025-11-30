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

    public int realizarEmprestimo(int userId, List<Mineral> minerais, List<Rocha> rochas) throws SQLException {
        String sqlEmprestimo = "INSERT INTO emprestimo (dataEmp, dataDev, usuario_idusuario) VALUES (CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY), ?)";
        String sqlMinerais = "INSERT INTO emprestimo_has_minerais (emprestimo_idemprestimo, emprestimo_usuario_idusuario, minerais_idminerais, minerais_site_idsite) VALUES (?, ?, ?, ?)";
        String sqlRochas = "INSERT INTO emprestimo_has_Rochas (emprestimo_idemprestimo, emprestimo_usuario_idusuario, Rochas_idRochas, Rochas_site_idsite) VALUES (?, ?, ?, ?)";

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
                    } else {
                        throw new SQLException("Falha ao obter ID do empréstimo");
                    }
                }
            }

            // 2 — Inserir minerais
            if (minerais != null && !minerais.isEmpty()) {
                try (PreparedStatement stmt = conn.prepareStatement(sqlMinerais)) {
                    for (Mineral m : minerais) {
                        stmt.setInt(1, emprestimoId);
                        stmt.setInt(2, userId);
                        stmt.setInt(3, m.getIdminerais());
                        stmt.setString(4, String.valueOf(m.getSite().getIdsite())); // site_idsite é VARCHAR
                        stmt.addBatch();
                    }
                    stmt.executeBatch();
                }
            }

            // 3 — Inserir rochas
            if (rochas != null && !rochas.isEmpty()) {
                try (PreparedStatement stmt = conn.prepareStatement(sqlRochas)) {
                    for (Rocha r : rochas) {
                        stmt.setInt(1, emprestimoId);
                        stmt.setInt(2, userId);
                        stmt.setInt(3, r.getIdRochas());
                        stmt.setInt(4, r.getSite().getIdsite());
                        stmt.addBatch();
                    }
                    stmt.executeBatch();
                }
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
                    emp.setUsuarioId(rs.getInt("usuario_idusuario"));
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
                emp.setUsuarioId(rs.getInt("usuario_idusuario"));
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