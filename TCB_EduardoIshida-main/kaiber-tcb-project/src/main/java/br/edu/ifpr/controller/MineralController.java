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
        this.conn = ConnectionFactory.getConnection(); // ✅ Obter conexão
        this.mineralDAO = new MineralDAO(conn);
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
        return mineralDAO.listarTodos();
    } catch (SQLException e) {
        System.err.println("Erro ao listar minerais: " + e.getMessage());
        return new ArrayList<>();
    }
}

}
