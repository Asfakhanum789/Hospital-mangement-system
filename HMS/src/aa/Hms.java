package aa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Hms {
    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String USER = "root";
    private static final String PASSWORD = "Asfakhanum@2002";

    // JDBC objects
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    // Establishing database connection
    public Hms() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new patient
    public void addPatient(String name, int age, String gender, String address) {
        try {
            String query = "INSERT INTO patients (name, age, gender, address) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, address);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Patient added successfully.");
            } else {
                System.out.println("Failed to add patient.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new doctor
    public void addDoctor(String name, String specialization) {
        try {
            String query = "INSERT INTO doctors (name, specialization) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, specialization);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Doctor added successfully.");
            } else {
                System.out.println("Failed to add doctor.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to schedule an appointment
    public void scheduleAppointment(int patientId, int doctorId, String date) {
        try {
            String query = "INSERT INTO appointments (patient_id, doctor_id, date) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, doctorId);
            preparedStatement.setString(3, date);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Appointment scheduled successfully.");
            } else {
                System.out.println("Failed to schedule appointment.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to close database connection
    public void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Hms hospitalManagementSystem = new Hms();

        // Example usage
        hospitalManagementSystem.addPatient("John Doe", 35, "Male", "123 Main St, City");
        hospitalManagementSystem.addDoctor("Dr. Smith", "Cardiology");
        hospitalManagementSystem.scheduleAppointment(1, 1, "2024-03-16");

        hospitalManagementSystem.closeConnection();
    }
}
