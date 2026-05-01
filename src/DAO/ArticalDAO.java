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
}