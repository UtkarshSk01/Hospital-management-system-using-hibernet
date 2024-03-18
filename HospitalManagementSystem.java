package com.hospitalmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class HospitalManagementSystem {
    private final HospitalDAO hospitalDAO;
    private final PatientDAO patientDAO;
    private final DoctorDAO doctorDAO;
    private final AppointmentDAO appointmentDAO;
    private final NurseDAO nurseDAO;
    private final BillingDAO billingDAO;

    public HospitalManagementSystem() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hib", "root", "2002utk");
            this.hospitalDAO = new HospitalDAO(connection);
            this.patientDAO = new PatientDAO(connection);
            this.doctorDAO = new DoctorDAO(connection);
            this.appointmentDAO = new AppointmentDAO(connection);
            this.nurseDAO = new NurseDAO(connection);
            this.billingDAO = new BillingDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    public void viewHospitals() {
        try {
            List<Hospital> hospitals = hospitalDAO.getAllHospitals();
            for (Hospital hospital : hospitals) {
                System.out.println(hospital);
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while retrieving hospitals: " + e.getMessage());
        }
    }

    public void viewPatients() {
        try {
            List<Patient> patients = patientDAO.getAllPatients();
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while retrieving patients: " + e.getMessage());
        }
    }

    public void viewDoctors() {
        try {
            List<Doctor> doctors = doctorDAO.getAllDoctors();
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while retrieving doctors: " + e.getMessage());
        }
    }

    public void viewAppointments() {
        try {
            List<Appointment> appointments = appointmentDAO.getAllAppointments();
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while retrieving appointments: " + e.getMessage());
        }
    }

    public void viewNurses() {
        try {
            List<Nurse> nurses = nurseDAO.getAllNurses();
            for (Nurse nurse : nurses) {
                System.out.println(nurse);
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while retrieving nurses: " + e.getMessage());
        }
    }

    public void viewBillings() {
        try {
            List<Billing> billings = billingDAO.getAllBillings();
            for (Billing billing : billings) {
                System.out.println(billing);
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while retrieving billings: " + e.getMessage());
        }
    }

    // Add methods for adding, updating, deleting, and retrieving specific entities

    public void addPatient(String name, int age, String gender) {
        try {
            Patient patient = new Patient(name, age, gender);
            patientDAO.addPatient(patient);
            System.out.println("Patient added successfully.");
        } catch (SQLException e) {
            System.err.println("Error occurred while adding patient: " + e.getMessage());
        }
    }
    public void addHospital(String name, String address) {
        try {
            Hospital hospital = new Hospital(name, address);
            hospitalDAO.addHospital(hospital);
            System.out.println("Hospital added successfully.");
        } catch (SQLException e) {
            System.err.println("Error occurred while adding hospital: " + e.getMessage());
        }
    }

    public void addDoctor(String name, String specialization) {
        try {
            Doctor doctor = new Doctor(name, specialization);
            doctorDAO.addDoctor(doctor);
            System.out.println("Doctor added successfully.");
        } catch (SQLException e) {
            System.err.println("Error occurred while adding doctor: " + e.getMessage());
        }
    }

    // Methods for updating entities
    public void updatePatient(int id, String name, int age, String gender) {
        try {
            Patient patient = new Patient(id, name, age, gender);
            patientDAO.updatePatient(patient);
            System.out.println("Patient updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error occurred while updating patient: " + e.getMessage());
        }
    }

    // Methods for deleting entities
    public void deletePatient(int id) {
        try {
            patientDAO.deletePatient(id);
            System.out.println("Patient deleted successfully.");
        } catch (SQLException e) {
            System.err.println("Error occurred while deleting patient: " + e.getMessage());
        }
    }
    // Add other methods for adding, updating, deleting, and retrieving specific entities

}
