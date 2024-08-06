package org.example;
import java.sql.*;

public class DataBase {
    // Connection to the Database
    public Connection getConnection(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null) {
                System.out.println("Connected to PostgreSQL database");
            } else {
                System.out.println("Failed to connect to PostgreSQL database");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    // Load users from table
    public void loadUser(Connection conn, String tableName) {
        Statement statement;
        ResultSet rs;
        try {
            String query = String.format("select * from %s", tableName);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getInt("id") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.print(rs.getShort("pin") + " ");
                System.out.println(rs.getDouble("sold") + " ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void searchUserByName(Connection conn, String tableName, String name) {
        Statement statement;
        ResultSet rs;
        try {
            String query = String.format("select * from %s where name = '%s'", tableName, name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getInt("id") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.print(rs.getShort("pin") + " ");
                System.out.println(rs.getDouble("sold") + " ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // Add an user to the table
    public void addUser(Connection conn, String tableName, String name, short pin, double sold) {
        Statement statement;
        try {
            String query = String.format("insert into %s(name, pin, sold) values('%s','%d','%f')", tableName, name, pin, sold);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.printf("User %s added to table %s.", name, tableName);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Remove an user to the table
    public void deleteUser(Connection conn, String tableName, int id) {
        Statement statement;
        try {
            String query = String.format("delete from %s where id = '%d'", tableName, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.printf("Id %d deleted from table %s.", id, tableName);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

