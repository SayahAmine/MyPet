package DAO;

import database.DBConnection;
import model.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PetDAO {

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
}
