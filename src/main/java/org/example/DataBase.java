package org.example;
import java.sql.*;

public class DataBase {
    public Connection getConnection(String dbname, String user, String pass) {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (con != null) {
                System.out.println("Connected to PostgreSQL database");
            } else {
                System.out.println("Failed to connect to PostgreSQL database");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public void addUser(Connection conn, String tableName, String name, short pin, double sold) {
        Statement statement;
        try {
            String query = String.format("Insert into %s(name, pin, sold) values('%s','%d','%f')", tableName, name, pin, sold);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.printf("User %s added to table %s.", name, tableName);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
