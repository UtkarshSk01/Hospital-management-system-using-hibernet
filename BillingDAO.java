package com.hospitalmanagement;

import com.hospitalmanagement.Billing;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {
    private Connection connection;

    public BillingDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Billing> getAllBillings() throws SQLException {
        List<Billing> billings = new ArrayList<>();
        String query = "SELECT * FROM billings";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Billing billing = new Billing();
                billing.setId(rs.getInt("id"));
                billing.setAppointmentId(rs.getInt("appointment_id"));
                billing.setAmount(rs.getDouble("amount"));
                billing.setStatus(rs.getString("status"));
                billings.add(billing);
            }
        }
        return billings;
    }

    public void addBilling(Billing billing) throws SQLException {
        String query = "INSERT INTO billings (appointment_id, amount, status) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, billing.getAppointmentId());
            stmt.setDouble(2, billing.getAmount());
            stmt.setString(3, billing.getStatus());
            stmt.executeUpdate();
        }
    }

    public void updateBilling(Billing billing) throws SQLException {
        String query = "UPDATE billings SET appointment_id=?, amount=?, status=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, billing.getAppointmentId());
            stmt.setDouble(2, billing.getAmount());
            stmt.setString(3, billing.getStatus());
            stmt.setInt(4, billing.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteBilling(int id) throws SQLException {
        String query = "DELETE FROM billings WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Billing getBillingById(int id) throws SQLException {
        String query = "SELECT * FROM billings WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Billing billing = new Billing();
                    billing.setId(rs.getInt("id"));
                    billing.setAppointmentId(rs.getInt("appointment_id"));
                    billing.setAmount(rs.getDouble("amount"));
                    billing.setStatus(rs.getString("status"));
                    return billing;
                }
            }
        }
        return null;
    }
}
