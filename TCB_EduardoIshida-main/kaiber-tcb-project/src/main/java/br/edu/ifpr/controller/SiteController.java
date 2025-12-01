package br.edu.ifpr.controller;

import br.edu.ifpr.model.Site;
import br.edu.ifpr.model.dao.SiteDAO;
import br.edu.ifpr.model.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;


public class SiteController {
    private SiteDAO siteDAO;
    private Connection conn;

    public SiteController() {
        this.conn = ConnectionFactory.getConnection();
        this.siteDAO = new SiteDAO(conn);
    }

    public void cadastrarSite(Site site) {
        try {
             siteDAO.inserir(site);
            System.out.println("Site cadastrado com sucesso! ID: " + site.getIdsite());
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar site: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public  void listarSites() {
        try {
            SiteDAO.Selecionar();
        } catch (SQLException e) {
            System.err.println("Erro ao listar sites: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public  Site selecionarSitePorID(int idsite) {
        Site site = null;
        try {
            site = SiteDAO.selecionarPorID(idsite);
        } catch (SQLException e) {
            System.err.println("Erro ao selecionar site por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return site;
    }
}