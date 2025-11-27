package br.edu.ifpr.model.dao;

import br.edu.ifpr.model.Site;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiteDAO {
    private final Connection conn;

    public SiteDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Site site) throws SQLException {
        String sql = "INSERT INTO site (idsite, nome, cidade, pais, propriedadeprivada) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, site.getNome());
        stmt.setString(2, site.getCidade());
        stmt.setString(3, site.getPais());
        stmt.setString(4, site.getPropriedadeprivada());
        stmt.executeUpdate();

        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                site.setIdsite(generatedKeys.getInt(1)); 
            }
        }
    }
    }}