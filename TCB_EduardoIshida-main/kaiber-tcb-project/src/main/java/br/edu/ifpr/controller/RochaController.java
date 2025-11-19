package br.edu.ifpr.controller;

import br.edu.ifpr.model.Rocha;
import br.edu.ifpr.model.dao.RochaDAO;

public class RochaController {
    private RochaDAO rochaDAO;

    public rockaControler() {
        this.rochaDAO = new RochaDAO();
    }
    public static void CadastrarRocha(Rocha rocha) {
      
        RochaDAO.CadastrarRocha(rocha);

    }
}
