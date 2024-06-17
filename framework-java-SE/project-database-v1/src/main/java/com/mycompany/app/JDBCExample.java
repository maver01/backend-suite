package com.mycompany.app;

// import necessary packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/testv1";
    private static final String USER = "mstestv1";
    private static final String PASSWORD = "test1234"; // Replace with the actual password

    // JDBC variables for opening and managing connection
    private static Connection connection;

    public static void main(String[] args) {
        try {
            System.out.println("Initializing database...");
            // Initialize the database
            initDB();
            System.out.println("Database initialized.");
            // Create a new record
            createPerson(1, "John", "Doe");
            // Create a new record
            createPerson(2, "Mary", "Rose");
            System.out.println("Person created.");
            // Read all records
            readPersons();
            System.out.println("Persons read.");
            // Update a record
            updatePerson(1, "Jane", "Doe");
            System.out.println("Person updated.");
            readPersons();
            // Delete a record
            deletePerson(2);
            System.out.println("Person deleted.");
            readPersons();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            if (connection != null) {
                System.out.println("Closing the connection.");
                try {
                    connection.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void initDB() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        if (connection != null) {
            System.out.println("Connected to the database.");
        }
    }

    private static void createPerson(Integer Id, String name, String lastName) throws SQLException {
        String query = "INSERT INTO Person (Id, name, last_name) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("A new person has been inserted.");
            }
        }
    }

    private static void readPersons() throws SQLException {
        String query = "SELECT * FROM Person";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                System.out.printf("ID: %d, Name: %s, Last Name: %s%n", id, name, lastName);
            }
        }
    }

    private static void updatePerson(int id, String name, String lastName) throws SQLException {
        String query = "UPDATE Person SET name = ?, last_name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, id);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Person with ID " + id + " has been updated.");
            }
        }
    }

    private static void deletePerson(int id) throws SQLException {
        String query = "DELETE FROM Person WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Person with ID " + id + " has been deleted.");
            }
        }
    }
}
