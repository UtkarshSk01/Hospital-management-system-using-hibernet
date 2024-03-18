package com.hospitalmanagement;


import com.hospitalmanagement.Appointment;
import com.hospitalmanagement.Patient;
import com.hospitalmanagement.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AppointmentDAO {
    private Connection connection;

    public AppointmentDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(rs.getInt("id"));
                appointment.setPatientId(rs.getInt("patient_id"));
                appointment.setDoctorId(rs.getInt("doctor_id"));
                appointment.setDate(rs.getDate("date"));
                appointment.setStatus(rs.getString("status"));
                appointments.add(appointment);
            }
        }
        return appointments;
    }

    public void addAppointment(Appointment appointment) throws SQLException {
        String query = "INSERT INTO appointments (patient_id, doctor_id, date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setDate(3, new Date(appointment.getDate().getTime()));
            stmt.setString(4, appointment.getStatus());
            stmt.executeUpdate();
        }
    }

    public void updateAppointment(Appointment appointment) throws SQLException {
        String query = "UPDATE appointments SET patient_id=?, doctor_id=?, date=?, status=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setDate(3, new Date(appointment.getDate().getTime()));
            stmt.setString(4, appointment.getStatus());
            stmt.setInt(5, appointment.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteAppointment(int id) throws SQLException {
        String query = "DELETE FROM appointments WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Appointment getAppointmentById(int id) throws SQLException {
        String query = "SELECT * FROM appointments WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Appointment appointment = new Appointment();
                    appointment.setId(rs.getInt("id"));
                    appointment.setPatientId(rs.getInt("patient_id"));
                    appointment.setDoctorId(rs.getInt("doctor_id"));
                    appointment.setDate(rs.getDate("date"));
                    appointment.setStatus(rs.getString("status"));
                    return appointment;
                }
            }
        }
        return null;
    }
}
