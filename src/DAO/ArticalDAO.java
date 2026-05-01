package DAO;

import database.DBConnection;
import stock.Artical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ArticalDAO {

    public long save(Artical a) {
        String sql = "INSERT INTO artical(name, quantity, lowquantity, price, expirationDate, category, unit) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, a.getName());
            ps.setInt(2, a.getQuantity());
            ps.setInt(3, a.getLowquantity());
            ps.setInt(4, a.getPrice());
            ps.setString(5, a.getExpirationDate().toString());
            ps.setString(6, a.getCategory().name());
            ps.setString(7, a.getUnit().name());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getLong(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return a.getId();
    }

    public void update(Artical artical) {
        String sql = "UPDATE artical SET name=?, quantity=?, lowquantity=?, price=?, expirationDate=?, category=?, unit=? WHERE id=?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, artical.getName());
            ps.setInt(2, artical.getQuantity());
            ps.setInt(3, artical.getLowquantity());
            ps.setInt(4, artical.getPrice());
            ps.setString(5, artical.getExpirationDate().toString());
            ps.setString(6, artical.getCategory().name());
            ps.setString(7, artical.getUnit().name());
            ps.setLong(8, artical.getId());
            ps.executeUpdate();

            System.out.println("Artical updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        String sql = "DELETE FROM artical WHERE id=?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

            System.out.println("Artical deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}