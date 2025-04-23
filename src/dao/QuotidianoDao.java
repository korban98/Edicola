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

    public Double selectRendiconto() throws SQLException {
        String sql = "SELECT SUM(prezzo_singolo * cvendute) AS rendiconto FROM quotidiani WHERE cvendute > 0";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                return rs.getDouble("rendiconto");
            }
        }

        return 0.0;
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

    public void insert(Quotidiano quotidiano) throws SQLException {
        String sql = "INSERT INTO quotidiani(nome,prezzo,aggio,cricevute,cvendute) VALUES (?,?,?,?,?)"; 
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, quotidiano.getNome());
            stmt.setDouble(1, quotidiano.getPrezzo());
            stmt.setInt(1, quotidiano.getAggio());
            stmt.setInt(1, quotidiano.getCopieRicevute());
            stmt.setInt(1, quotidiano.getCopieVendute());

            stmt.executeUpdate();
        } 
    }
}
