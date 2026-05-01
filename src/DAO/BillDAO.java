package DAO;

import database.DBConnection;
import model.Appointment;
import stock.Artical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BillDAO {

    public long save(Appointment.Bill bill, long appointmentId) {

        String sql = "INSERT INTO bill(amount, appointment_id) VALUES(?,?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, bill.calculateTotal());
            ps.setLong(2, appointmentId);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                bill.setId(rs.getLong(1));
            }

            // Save relation with articles!!!!
            saveBillArticals(conn, bill);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bill.getId();
    }

    private void saveBillArticals(Connection conn, Appointment.Bill bill) throws Exception {

        String sql = "INSERT INTO bill_artical(bill_id, artical_id) VALUES(?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        for (Artical a : bill.getArticalsUsed()) {
            ps.setLong(1, bill.getId());
            ps.setLong(2, a.getId());
            ps.addBatch();
        }

        ps.executeBatch();
    }

    public void update(Appointment.Bill bill) {
        String sql = "UPDATE bill SET amount=? WHERE id=?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, bill.calculateTotal());
            ps.setLong(2, bill.getId());
            ps.executeUpdate();

            // Update bill–artical relations
            deleteBillArticals(conn, bill.getId());
            saveBillArticals(conn, bill);

            System.out.println("Bill updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        String sql = "DELETE FROM bill WHERE id=?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // First delete relations
            deleteBillArticals(conn, id);

            ps.setLong(1, id);
            ps.executeUpdate();

            System.out.println("Bill deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteBillArticals(Connection conn, long billId) throws Exception {
        String sql = "DELETE FROM bill_artical WHERE bill_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, billId);
            ps.executeUpdate();
        }
    }

}