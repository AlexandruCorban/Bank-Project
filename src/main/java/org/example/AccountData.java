package org.example;

import java.sql.*;

public class AccountData {
    public static class AccountInfo {
        private String name;
        private String phoneNumber;
        private short pin;
        private double sold;

        public AccountInfo(String name, String phoneNumber, short pin, double sold) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.pin = pin;
            this.sold = sold;
        }

        public String getName() {
            return name;
        }
        public double getSold() {
            return sold;
        }
        public short getPin() { return pin; }
        public String getPhoneNumber() { return phoneNumber; }

    }

    public static AccountInfo getAccountData(String tableName, String iban) {
        DataBase db = new DataBase();
        try (Connection conn = db.getConnection(Login.dbName, Login.dbUsername, Login.dbPassword); Statement statement = conn.createStatement()) {
            String query = String.format("SELECT * FROM %s WHERE iban = '%s'", tableName, iban);

            String name;
            String phoneNumber;
            short pin;
            double sold;

            try (ResultSet rs = statement.executeQuery(query)) {
                if (rs.next()) {
                    name = rs.getString("name");
                    phoneNumber = rs.getString("phonenumber");
                    pin = rs.getShort("pin");
                    sold = rs.getDouble("sold");
                    return new AccountInfo(name, phoneNumber, pin, sold);
                }
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
        return new AccountInfo("User not exist", " ", (short) 0, 0.0);
    }
}