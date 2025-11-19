package br.edu.ifpr.controller;

import br.edu.ifpr.model.dao.MineralDAO;

public class MineralController {
private MineralDAO;
    public MineralController() {
        this.MineralDAO = new MineralDAO();
    }

    public static void CadastrarMineral(Mineral mineral) {

        MineralDAO.CadastrarMineral(mineral);

    }
}
