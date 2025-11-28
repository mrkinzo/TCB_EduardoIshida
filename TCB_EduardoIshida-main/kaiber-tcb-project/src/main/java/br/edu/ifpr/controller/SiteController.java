package br.edu.ifpr.controller;

import br.edu.ifpr.model.Site;
import br.edu.ifpr.model.dao.SiteDAO;
import br.edu.ifpr.model.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

    // ✅ CORREÇÃO: Implementar busca real
    public Site buscarSitePorId(int id) {
        // Por enquanto retorna null - implementar quando SiteDAO tiver o método
        System.out.println("Busca de site por ID ainda não implementada");
        return null;
    }

    // ✅ CORREÇÃO: Implementar listagem real
    public List<Site> listarTodosSites() {
        // Por enquanto retorna lista vazia - implementar quando SiteDAO tiver o método
        System.out.println("Listagem de sites ainda não implementada");
        return new java.util.ArrayList<>();
    }
}