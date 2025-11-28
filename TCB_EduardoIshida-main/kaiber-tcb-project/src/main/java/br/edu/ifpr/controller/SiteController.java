package br.edu.ifpr.controller;

import br.edu.ifpr.model.Site;
import br.edu.ifpr.model.dao.SiteDAO;
import br.edu.ifpr.model.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

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

    public Site buscarSitePorId(int id) {
        // Implementar busca por ID
        return null;
    }

    public List<Site> listarTodosSites() {
        // Implementar listagem
        return new ArrayList<>();
    }
}