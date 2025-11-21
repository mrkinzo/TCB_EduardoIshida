package br.edu.ifpr.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.model.Mineral;
import br.edu.ifpr.model.dao.MineralDAO;

public class MineralController {
private MineralDAO;
    public MineralController() {
        this.MineralDAO = new MineralDAO();
    }

    public static void CadastrarMineral(Mineral mineral) {

        MineralDAO.CadastrarMineral(mineral);
        
    }
    public Mineral buscarMineralPorId(int id) {
    try {
        return mineralDAO.buscarPorId(id);
    } catch (SQLException e) {
        System.err.println("Erro ao buscar mineral: " + e.getMessage());
        return null;
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
