package DAO;


import database.DBConnection;
import model.Owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class OwnerDAO {

    public Long save(Owner owner) {
        String sql = "INSERT INTO owner(name, email, phone) VALUES(?,?,?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, owner.getName());
            ps.setString(2, owner.getEmail());
            ps.setInt(3, owner.getPhoneNumber());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                owner.setId(rs.getLong(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return owner.getId();
    }
}