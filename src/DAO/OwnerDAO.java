package DAO;


import database.DBConnection;
import model.Owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class OwnerDAO {

    public Owner findById(long id) {
        String sql = "SELECT * FROM owner WHERE id=?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Owner owner = new Owner();
                owner.setId(rs.getLong("id"));
                owner.setName(rs.getString("name"));
                owner.setEmail(rs.getString("email"));
                owner.setPhoneNumber(rs.getInt("phone"));
                return owner;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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

    public void update(Owner owner) {
        String sql = "UPDATE owner SET name=?, email=?, phone=? WHERE id=?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, owner.getName());
            ps.setString(2, owner.getEmail());
            ps.setInt(3, owner.getPhoneNumber());
            ps.setLong(4, owner.getId());
            ps.executeUpdate();

            System.out.println("Owner updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        String sql = "DELETE FROM owner WHERE id=?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

            System.out.println("Owner deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}