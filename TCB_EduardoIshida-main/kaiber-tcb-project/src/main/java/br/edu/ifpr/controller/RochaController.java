package br.edu.ifpr.controller;

import br.edu.ifpr.model.Rocha;
import br.edu.ifpr.model.dao.ConnectionFactory;
import br.edu.ifpr.model.dao.RochaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RochaController {
    private RochaDAO rochaDAO;

    public RochaController() {
        Connection conn = ConnectionFactory.getConnection();
        this.rochaDAO = new RochaDAO(conn);
    }

    public void cadastrarRocha(Rocha rocha) {
        try {
            rochaDAO.inserir(rocha);
            System.out.println("Rocha cadastrada com sucesso! ID: " + rocha.getIdRochas());
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar rocha: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Rocha buscarRochaPorId(int id) {
        try {
            return rochaDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar rocha: " + e.getMessage());
            return null;
        }
    }

    public List<Rocha> listarTodasRochas() {
        try {
            return rochaDAO.listarTodas();
        } catch (SQLException e) {
            System.err.println("Erro ao listar rochas: " + e.getMessage());
            return null;
        }
    }

    

    
    public void atualizarRocha(Rocha rocha) {
        try {
            rochaDAO.atualizar(rocha);
            System.out.println("Rocha atualizada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar rocha: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deletarRocha(int id) {
        try {
            rochaDAO.deletar(id);
            System.out.println("Rocha deletada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar rocha: " + e.getMessage());
            e.printStackTrace();
        }
    }

}