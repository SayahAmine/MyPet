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


    public void update(Appointment appointment) {
        String sql = "UPDATE appointment SET pet_id=?, date=? WHERE id=?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, appointment.getPet().getId());
            ps.setString(2, appointment.getDate().toString());
            ps.setLong(3, appointment.getId());
            ps.executeUpdate();

            System.out.println("Appointment updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        String sql = "DELETE FROM appointment WHERE id=?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

            System.out.println("Appointment deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}