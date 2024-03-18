package com.hospitalmanagement;


import com.hospitalmanagement.Hospital;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalDAO {
    private Connection connection;

    public HospitalDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Hospital> getAllHospitals() throws SQLException {
        List<Hospital> hospitals = new ArrayList<>();
        String query = "SELECT * FROM hospitals";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Hospital hospital = new Hospital();
                hospital.setId(rs.getInt("id"));
                hospital.setName(rs.getString("name"));
                hospital.setAddress(rs.getString("address"));
                hospitals.add(hospital);
            }
        }
        return hospitals;
    }

    public Hospital getHospitalById(int id) throws SQLException {
        String query = "SELECT * FROM hospitals WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Hospital hospital = new Hospital();
                    hospital.setId(rs.getInt("id"));
                    hospital.setName(rs.getString("name"));
                    hospital.setAddress(rs.getString("address"));
                    return hospital;
                }
            }
        }
        return null;
    }

    public void addHospital(Hospital hospital) throws SQLException {
        String query = "INSERT INTO hospitals (name, address) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hospital.getName());
            stmt.setString(2, hospital.getAddress());
            stmt.executeUpdate();
        }
    }

    public void updateHospital(Hospital hospital) throws SQLException {
        String query = "UPDATE hospitals SET name = ?, address = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hospital.getName());
            stmt.setString(2, hospital.getAddress());
            stmt.setInt(3, hospital.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteHospital(int id) throws SQLException {
        String query = "DELETE FROM hospitals WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
