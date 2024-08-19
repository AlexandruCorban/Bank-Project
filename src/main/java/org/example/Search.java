package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Search {
    DataBase db = new DataBase();

    public void searchUserByName(String tableName, String name) {
        try (Connection conn = db.getConnection(Login.dbName, Login.dbUsername, Login.dbPassword); Statement statement = conn.createStatement()) {
            String query = String.format("SELECT * FROM %s WHERE name = '%s'", tableName, name);

            try (ResultSet rs = statement.executeQuery(query)) {
                while (rs.next()) {
                    String iban = rs.getString("iban");
                    String fullname = rs.getString("name");
                    String phoneNumber = (rs.getString("phonenumber") + " ");
                    short pin = rs.getShort("pin");
                    double sold = rs.getDouble("sold");

                    System.out.printf("%s %s %s %d %.2f%n", iban, fullname, phoneNumber, pin, sold);
                }
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public void searchUserByIban(String tableName, String Iban) {
        try (Connection conn = db.getConnection(Login.dbName, Login.dbUsername, Login.dbPassword); Statement statement = conn.createStatement()) {
            String query = String.format("SELECT * FROM %s WHERE iban = '%s'", tableName, Iban);

            try (ResultSet rs = statement.executeQuery(query)) {
                while (rs.next()) {
                    String iban = rs.getString("iban");
                    String fullname = rs.getString("name");
                    String phoneNumber = (rs.getString("phonenumber") + " ");
                    short pin = rs.getShort("pin");
                    double sold = rs.getDouble("sold");

                    System.out.printf("%s %s %s %d %.2f%n", iban, fullname, phoneNumber, pin, sold);
                }
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }
    public void searchUserByPhoneNumber(String tableName, String phoneNumber) {
        try (Connection conn = db.getConnection(Login.dbName, Login.dbUsername, Login.dbPassword); Statement statement = conn.createStatement()) {
            String query = String.format("SELECT * FROM %s WHERE phonenumber = '%s'", tableName, phoneNumber);

            try (ResultSet rs = statement.executeQuery(query)) {
                while (rs.next()) {
                    String iban = rs.getString("iban");
                    String fullname = rs.getString("name");
                    String phonenumber = (rs.getString("phonenumber") + " ");
                    short pin = rs.getShort("pin");
                    double sold = rs.getDouble("sold");

                    System.out.printf("%s %s %s %d %.2f%n", iban, fullname, phonenumber, pin, sold);
                }
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public void filterDescSold(String tableName, int size) {
        try (Connection conn = db.getConnection(Login.dbName, Login.dbUsername, Login.dbPassword); Statement statement = conn.createStatement()) {
            String query = String.format("SELECT * FROM %s ORDER BY sold DESC", tableName);

            try (ResultSet rs = statement.executeQuery(query)) {
                while (rs.next() && size > 0) {
                    String iban = rs.getString("iban");
                    String fullname = rs.getString("name");
                    String phoneNumber = (rs.getString("phonenumber") + " ");
                    short pin = rs.getShort("pin");
                    double sold = rs.getDouble("sold");

                    System.out.printf("%d %s %s %s %d %.2f%n", (100 - size + 1), iban, phoneNumber, fullname, pin, sold);
                    size--;
                }
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public void filterAscSold(String tableName, int size) {
        try (Connection conn = db.getConnection(Login.dbName, Login.dbUsername, Login.dbPassword); Statement statement = conn.createStatement()) {
            String query = String.format("SELECT * FROM %s ORDER BY sold ASC", tableName);

            try (ResultSet rs = statement.executeQuery(query)) {
                while (rs.next() && size > 0) {
                    String iban = rs.getString("iban");
                    String fullname = rs.getString("name");
                    String phoneNumber = (rs.getString("phonenumber") + " ");
                    short pin = rs.getShort("pin");
                    double sold = rs.getDouble("sold");

                    System.out.printf("%d %s %s %s %d %.2f%n", (100 - size + 1), iban, fullname, phoneNumber, pin, sold);
                    size--;
                }
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }
}