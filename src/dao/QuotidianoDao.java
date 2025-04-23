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

    public void updateRicevuteEVendute(int id, int ricevute, int vendute) throws SQLException {
        String sql = "UPDATE quotidiani SET cricevute = ?, cvendute = ? WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, ricevute);
            stmt.setInt(2, vendute);
            stmt.setInt(3, id);
    
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
        String updateSql = "UPDATE quotidiani SET cvendute = cvendute + 1 WHERE id = ? AND cvendute < cricevute";
    
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
    
            updateStmt.setInt(1, id);
            int rowsUpdated = updateStmt.executeUpdate();
    
            if (rowsUpdated == 0) {
                System.out.println("Impossibile incrementare: tutte le copie sono già vendute o l'id non esiste.");
            }
        }
    }
    

    public Double selectRendiconto() throws SQLException {
        String sql = "SELECT nome, cricevute, cvendute, (prezzo * cvendute) AS totale_per_quot FROM quotidiani WHERE cvendute > 0";
        double totale = 0;

        try (Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getString("nome") + " - cvendute: " + rs.getInt("cvendute") + " - cricevute: " + rs.getInt("cricevute") + " - Rese: " + (rs.getInt("cricevute")-rs.getInt("cvendute")) + " - Tot per quot.: " + rs.getDouble("totale_per_quot"));
                totale += rs.getDouble("totale_per_quot");
            }
        }

        return totale;
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

    public void modificaPrezzo(int id, double prezzo) throws SQLException {
        String sql = "UPDATE quotidiani SET prezzo = ? WHERE cvendute = 0 AND id=?";
        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, prezzo);
            stmt.setInt(2, id);
            double result=stmt.executeUpdate();
            if (result > 0) {
                System.out.println("Prezzo aggiornato correttamente");
            } else {
                System.out.println("Errore nell'aggiornamento");
            }
        }
    }

    public void modificaAggio(int id, int aggio) throws SQLException {
        String sql = "UPDATE quotidiani SET aggio = ? WHERE cvendute = 0 AND id=?";
   
        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, aggio);
            stmt.setInt(2, id);
            int result=stmt.executeUpdate();
            if (result > 0) {
                System.out.println("Aggio aggiornato correttamente");
            } else {
                System.out.println("Errore nell'aggiornamento");
            }
        }
    }

    public void reset() throws SQLException {
        String sql = "UPDATE quotidiani SET cricevute = 0 AND cvendute = 0";
   
        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            int result=stmt.executeUpdate();
            if (result > 0) {
                System.out.println("reset effettuato correttamente");
            } else {
                System.out.println("Errore nell'aggiornamento");
            }
        }
    }
   
}
