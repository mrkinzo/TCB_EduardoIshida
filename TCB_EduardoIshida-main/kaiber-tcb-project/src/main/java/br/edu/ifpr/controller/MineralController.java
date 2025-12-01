package br.edu.ifpr.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifpr.model.Mineral;
import br.edu.ifpr.model.dao.MineralDAO;
import br.edu.ifpr.model.dao.ConnectionFactory;

public class MineralController {
    private MineralDAO mineralDAO;
    private Connection conn;

    public MineralController() {
        this.conn = ConnectionFactory.getConnection();
        this.mineralDAO = new MineralDAO(conn);
    }

    public Mineral buscarMineralPorId(int id) {
        try {
            return mineralDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar mineral: " + e.getMessage());
            return null;
        }
    }
    public void cadastrarMineral(Mineral mineral) {
        try {
            mineralDAO.inserir(mineral);
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar mineral: " + e.getMessage());
        }
    }

    public List<Mineral> listarTodosMinerais() {
        try {
            return mineralDAO.ListarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar minerais: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void atualizarMineral(Mineral mineral) {
        try {
            mineralDAO.atualizar(mineral);
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar mineral: " + e.getMessage());
        }
    }

    public void deletarMineral(int mineralId) {
        try {
            mineralDAO.deletar(mineralId);
        } catch (SQLException e) {
            System.err.println("Erro ao deletar mineral: " + e.getMessage());
        }
    }
}