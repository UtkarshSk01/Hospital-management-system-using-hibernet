package com.hospitalmanagement;

import com.hospitalmanagement.Nurse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NurseDAO {
    private Connection connection;

    public NurseDAO(Connection connection) {
        this.connection = connection;
    }
    public NurseDAO() {
		// TODO Auto-generated constructor stub
	}

	public List<Nurse> getAllNurses() throws SQLException {
        List<Nurse> nurses = new ArrayList<>();
        String query = "SELECT * FROM nurses";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Nurse nurse = new Nurse();
                nurse.setId(rs.getInt("id"));
                nurse.setName(rs.getString("name"));
                nurses.add(nurse);
            }
        }
        return nurses;
    }

    public void addNurse(Nurse nurse) throws SQLException {
        String query = "INSERT INTO nurses (name) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nurse.getName());
            stmt.executeUpdate();
        }
    }

    public void updateNurse(Nurse nurse) throws SQLException {
        String query = "UPDATE nurses SET name=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nurse.getName());
            stmt.setInt(2, nurse.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteNurse(int id) throws SQLException {
        String query = "DELETE FROM nurses WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Nurse getNurseById(int id) throws SQLException {
        String query = "SELECT * FROM nurses WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Nurse nurse = new Nurse();
                    nurse.setId(rs.getInt("id"));
                    nurse.setName(rs.getString("name"));
                    return nurse;
                }
            }
        }
        return null;
    }
}
