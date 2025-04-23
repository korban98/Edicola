package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Quotidiano;

public class QuotidianoDao {
    static final String url = "jdbc:mysql://localhost:3306/edicola";
    static final String user = "root"; 
    static final String password = ""; 

    public List<Quotidiano> selectAll() throws SQLException {
        List<Quotidiano> quotidiani = new ArrayList<>();
        String sql = "SELECT * FROM quotidiani";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                quotidiani.add(new Quotidiano(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("cricevute"),
                        rs.getDouble("prezzo"),
                        rs.getInt("aggio"),
                        rs.getInt("cvendute")
                ));
            }
        }

        return quotidiani;
    }

    public Quotidiano selectGenereById(int id) throws SQLException {
        String sql = "SELECT * FROM genere WHERE id_genere = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); 

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Quotidiano(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("cricevute"),
                        rs.getDouble("prezzo"),
                        rs.getInt("aggio"),
                        rs.getInt("cvendute")
                    );
                }
            }
        }

        return null; 
    }

    public void insert(Quotidiano genere) throws SQLException {
        String sql = "INSERT INTO genere(nome) VALUES (?)"; 
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, genere.getNome());

            stmt.executeUpdate();
        } 
    }
}
