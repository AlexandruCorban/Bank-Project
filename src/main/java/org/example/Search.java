package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Search {

    Statement statement = null;
    ResultSet rs = null;

    public void searchUserByName(Connection conn, String tableName, String name) {
        try {
            String query = String.format("SELECT * FROM %s WHERE name = '%s'", tableName, name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                String iban = rs.getString("iban");
                String fullname = rs.getString("name");
                String phoneNumber = (rs.getString("phonenumber") + " ");
                short pin = rs.getShort("pin");
                double sold = rs.getDouble("sold");

                System.out.printf("%s %s %s %d %.2f%n", iban, fullname, phoneNumber, pin, sold);
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public void searchUserByIban(Connection conn, String tableName, String Iban) {
        try {
            String query = String.format("SELECT * FROM %s WHERE iban = '%s'", tableName, Iban);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                String iban = rs.getString("iban");
                String fullname = rs.getString("name");
                String phoneNumber = (rs.getString("phonenumber") + " ");
                short pin = rs.getShort("pin");
                double sold = rs.getDouble("sold");

                System.out.printf("%s %s %s %d %.2f%n", iban, fullname, phoneNumber, pin, sold);
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }
    public void searchUserByPhoneNumber(Connection conn, String tableName, String phoneNumber) {
        try {
            String query = String.format("SELECT * FROM %s WHERE phonenumber = '%s'", tableName, phoneNumber);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                String iban = rs.getString("iban");
                String fullname = rs.getString("name");
                String phonenumber = (rs.getString("phonenumber") + " ");
                short pin = rs.getShort("pin");
                double sold = rs.getDouble("sold");

                System.out.printf("%s %s %s %d %.2f%n", iban, fullname, phonenumber, pin, sold);
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public void filterDescSold(Connection conn, String tableName, int size) {
        try {
            String query = String.format("SELECT * FROM %s ORDER BY sold DESC", tableName);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next() && size > 0) {
                String iban = rs.getString("iban");
                String fullname = rs.getString("name");
                String phoneNumber = (rs.getString("phonenumber") + " ");
                short pin = rs.getShort("pin");
                double sold = rs.getDouble("sold");

                System.out.printf("%d %s %s %s %d %.2f%n",(100 - size + 1) ,iban, phoneNumber, fullname, pin, sold);
                size--;
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public void filterAscSold(Connection conn, String tableName, int size) {
        try {
            String query = String.format("SELECT * FROM %s ORDER BY sold ASC", tableName);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next() && size > 0) {
                String iban = rs.getString("iban");
                String fullname = rs.getString("name");
                String phoneNumber = (rs.getString("phonenumber") + " ");
                short pin = rs.getShort("pin");
                double sold = rs.getDouble("sold");

                System.out.printf("%d %s %s %s %d %.2f%n",(100 - size + 1) ,iban, fullname, phoneNumber, pin, sold);
                size--;
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }
}
