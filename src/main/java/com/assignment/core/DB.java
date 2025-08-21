package com.assignment.core;

import java.sql.*;

public class DB {
    private static Connection conn;

    public static synchronized Connection connection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
                conn.createStatement().execute("CREATE TABLE IF NOT EXISTS logs (id IDENTITY, message VARCHAR(255))");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

    public static void log(String message) {
        try (PreparedStatement ps = connection().prepareStatement("INSERT INTO logs(message) VALUES(?)")) {
            ps.setString(1, message);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
