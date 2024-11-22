package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection connection;
    /**
     * Menginisialisasi koneksi ke database MySQL.
     * @return Connection objek
     */
    public static Connection getConnection() {
        if (connection == null) {
            String url = "jdbc:mysql://localhost/restapi"; 
            String username = "root"; 
            String password = ""; 

            try {
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Database connection established successfully.\n");
            } catch (SQLException e) {
                System.err.println("Database connection failed.\n");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("\nThe database connection is closed.");
            } catch (SQLException e) {
                System.err.println("\nFailed to close the database connection.");
                e.printStackTrace();
            }
        }
    }

    public static void initializeShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            closeConnection();
        }));
    }
}

