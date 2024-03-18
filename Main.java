package com.hospitalmanagement;

import java.util.Scanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            operateMenu();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private static void operateMenu() {
        Scanner scanner = new Scanner(System.in);
        HospitalManagementSystem hospitalManagementSystem = new HospitalManagementSystem();

        boolean exit = false;
        while (!exit) {
            System.out.println("Hospital Management System");
            System.out.println("1. View Hospitals");
            System.out.println("2. View Patients");
            System.out.println("3. View Doctors");
            System.out.println("4. Add Patient");
            System.out.println("5. Update Patient");
            System.out.println("6. Delete Patient");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    hospitalManagementSystem.viewHospitals();
                    break;
                case 2:
                    hospitalManagementSystem.viewPatients();
                    break;
                case 3:
                    hospitalManagementSystem.viewDoctors();
                    break;
                case 4:
                    addPatient(hospitalManagementSystem, scanner);
                    break;
                case 5:
                    updatePatient(hospitalManagementSystem, scanner);
                    break;
                case 6:
                    deletePatient(hospitalManagementSystem, scanner);
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void addPatient(HospitalManagementSystem hospitalManagementSystem, Scanner scanner) {
        System.out.println("Enter patient name:");
        String name = scanner.nextLine();
        System.out.println("Enter patient age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter patient gender:");
        String gender = scanner.nextLine();

        hospitalManagementSystem.addPatient(name, age, gender);
    }

    private static void updatePatient(HospitalManagementSystem hospitalManagementSystem, Scanner scanner) {
        System.out.println("Enter patient ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new name:");
        String name = scanner.nextLine();
        System.out.println("Enter new age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new gender:");
        String gender = scanner.nextLine();

        hospitalManagementSystem.updatePatient(id, name, age, gender);
    }

    private static void deletePatient(HospitalManagementSystem hospitalManagementSystem, Scanner scanner) {
        System.out.println("Enter patient ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        hospitalManagementSystem.deletePatient(id);
    }
}

