package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Quotidiano;

public class QuotidianoDao {
    static final String url = "jdbc:mysql://localhost:3306/edicola";
    static final String user = "root"; 
    static final String password = ""; 

    public void selectAll() throws SQLException {
        String sql = "SELECT * FROM quotidiani";

        try (Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("nome") + " - " + rs.getInt("cricevute") + " - " + rs.getDouble("prezzo") + " - " + rs.getInt("aggio") + " - " + rs.getInt("cvendute"));
            }
        }
    }

    public void updateRicevuteEVendute(Quotidiano quotidiano) throws SQLException {
        String sql = "UPDATE quotidiani SET cricevute = ?, cvendute = ? WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, quotidiano.getCopieRicevute());
            stmt.setInt(2, quotidiano.getCopieVendute());
            stmt.setInt(3, quotidiano.getId());
    
            stmt.executeUpdate();
        }
    }
    
    public void delete(Quotidiano quotidiano) throws SQLException {
        String sql = "DELETE FROM quotidiani WHERE id = ?";
    
        try (Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, quotidiano.getId());
            stmt.executeUpdate();
        }
    }
    
    public void incrementaCvendute(int id) throws SQLException {
        String selectSql = "SELECT cricevute, cvendute FROM quotidiani WHERE id = ?";
        String updateSql = "UPDATE quotidiani SET cvendute = cvendute + 1 WHERE id = ?";
    
        try (Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
    
            selectStmt.setInt(1, id);
            ResultSet rs = selectStmt.executeQuery();
    
            if (rs.next()) {
                int ricevute = rs.getInt("cricevute");
                int vendute = rs.getInt("cvendute");
    
                if (vendute < ricevute) {
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, id);
                        updateStmt.executeUpdate();
                    }
                } else {
                    System.out.println("Impossibile incrementare: tutte le copie ricevute sono giÃ state vendute.");
                }
            } else {
                System.out.println("Quotidiano non trovato con id = " + id);
            }
        }
    }

    public Double selectRendiconto() throws SQLException {
        String sql = "SELECT SUM(prezzo * cvendute) AS rendiconto FROM quotidiani WHERE cvendute > 0";

        try (Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                return rs.getDouble("rendiconto");
            }
        }

        return 0.0;
    }

    public void insert(Quotidiano quotidiano) throws SQLException {
        String sql = "INSERT INTO quotidiani(nome,prezzo,aggio,cricevute,cvendute) VALUES (?,?,?,?,?)"; 
        try (Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, quotidiano.getNome());
            stmt.setDouble(2, quotidiano.getPrezzo());
            stmt.setInt(3, quotidiano.getAggio());
            stmt.setInt(4, quotidiano.getCopieRicevute());
            stmt.setInt(5, quotidiano.getCopieVendute());

            stmt.executeUpdate();
        } 
    }

    public void modificaPrezzo(Quotidiano quotidiani) throws SQLException {
        String sql = "UPDATE quotidiani SET prezzo = ? WHERE cvendute = 0 AND nome=?";
        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, quotidiani.getPrezzo());
            stmt.setString(2, quotidiani.getNome());
            double result=stmt.executeUpdate();
            if (result > 0) {
                System.out.println("Prezzo aggiornato correttamente");
            } else {
                System.out.println("Errore nell'aggiornamento");
            }
        }
    }

    public void modificaAggio(Quotidiano quotidiani) throws SQLException {
        String sql = "UPDATE quotidiani SET aggio = ? WHERE cvendute = 0 AND nome=?";
   
        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quotidiani.getAggio());
            stmt.setString(2, quotidiani.getNome());
            int result=stmt.executeUpdate();
            if (result > 0) {
                System.out.println("Aggio aggiornato correttamente");
            } else {
                System.out.println("Errore nell'aggiornamento");
            }
        }
    }
   
}
