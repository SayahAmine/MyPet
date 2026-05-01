package DAO;

import model.Appointment;
import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AppointmentDAO {

    public long save(Appointment a) {
        String sql = "INSERT INTO appointment(pet_id, date) VALUES(?,?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, a.getPet().getId());
            ps.setString(2, a.getDate().toString());
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