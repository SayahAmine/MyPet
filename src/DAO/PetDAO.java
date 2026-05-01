package DAO;

import database.DBConnection;
import model.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PetDAO {

    public Pet findById(long id) {
        String sql = "SELECT * FROM pet WHERE id=?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pet pet = new Pet();
                pet.setId(rs.getLong("id"));
                pet.setName(rs.getString("name"));
                pet.setRace(rs.getString("race"));
                pet.setAge(rs.getInt("age"));
                pet.setWeight(rs.getInt("weight"));
                pet.setNeutered(rs.getBoolean("isNeutered"));
                // optionally fetch owner_id if needed
                return pet;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public long save(Pet pet) {
        String sql = "INSERT INTO pet(name, race, age, weight, isNeutered, owner_id) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, pet.getName());
            ps.setString(2, pet.getRace());
            ps.setInt(3, pet.getAge());
            ps.setInt(4, pet.getWeight());
            ps.setBoolean(5, pet.isNeutered());
            ps.setLong(6, pet.getOwner().getId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pet.setId(rs.getLong(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pet.getId();
    }

    public void update(Pet pet) {
        String sql = "UPDATE pet SET name=?, race=?, age=?, weight=?, isNeutered=?, owner_id=? WHERE id=?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pet.getName());
            ps.setString(2, pet.getRace());
            ps.setInt(3, pet.getAge());
            ps.setInt(4, pet.getWeight());
            ps.setBoolean(5, pet.isNeutered());
            ps.setLong(6, pet.getOwner().getId());
            ps.setLong(7, pet.getId());
            ps.executeUpdate();

            System.out.println("Pet updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        String sql = "DELETE FROM pet WHERE id=?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

            System.out.println("Pet deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
