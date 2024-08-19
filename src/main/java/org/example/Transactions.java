package org.example;

import java.sql.*;

public class Transactions {
    DataBase db = new DataBase();

    public void addSold(String tableName, String iban, double amount) {
        try (Connection conn = db.getConnection(Login.dbName, Login.dbUsername, Login.dbPassword); Statement statement = conn.createStatement()) {
            AccountData.AccountInfo accountInfo = AccountData.getAccountData(tableName, iban);
            String name = accountInfo.getName();

            String query = String.format("UPDATE %s SET sold = sold + %f WHERE iban = '%s'", tableName, amount, iban);
            int error = statement.executeUpdate(query);

            if (error != 0) {
                Logs.log(String.format("The amount of %.2f has been successfully deposited for %s with IBAN: %s.", amount, name, iban), "logsTransactions.txt");
                System.out.printf("The amount of %.2f has been successfully deposited for %s.\n", amount, name);
            } else {
                Logs.log(String.format("Deposit failed because IBAN: %s does not exist.", iban), "logsTransactions.txt");
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public void removeSold(String tableName, String iban, double amount) {
        try (Connection conn = db.getConnection(Login.dbName, Login.dbUsername, Login.dbPassword); Statement statement = conn.createStatement()) {
            AccountData.AccountInfo accountInfo = AccountData.getAccountData(tableName, iban);
            String nameUser = accountInfo.getName();
            double soldUser = accountInfo.getSold();

            String query = String.format("UPDATE %s SET sold = sold - %f WHERE iban = '%s' AND sold >= %f", tableName, amount, iban, amount);
            int error = statement.executeUpdate(query);

            if (error != 0) {
                Logs.log(String.format("The amount of %.2f has been successfully withdrawn for %s with IBAN: %s.", amount, nameUser, iban), "logsTransactions.txt");
                System.out.printf("The amount of %.2f has been successfully withdrawn for %s.\n", amount, nameUser);
            } else if (soldUser < amount && nameUser != "User not exist") {
                Logs.log(String.format("Withdraw failed for %s with IBAN: %s because sold less then ammount.", nameUser, iban), "logsTransactions.txt");
            } else {
                Logs.log(String.format("Withdraw failed because user does not exist.", iban), "logsTransactions.txt");
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public void transferSoldFromUser1toUser2(String tableName, String iban1, String iban2, double amount) {
        try (Connection conn = db.getConnection(Login.dbName, Login.dbUsername, Login.dbPassword); Statement statement = conn.createStatement()) {
            AccountData.AccountInfo accountInfo = AccountData.getAccountData(tableName, iban1);
            String nameUser1 = accountInfo.getName();
            double soldUser1 = accountInfo.getSold();

            accountInfo = AccountData.getAccountData(tableName, iban2);
            String nameUser2 = accountInfo.getName();

            String queryRemoveSoldFromUser1 = String.format("UPDATE %s SET sold = sold - %f WHERE iban = '%s' AND sold >= %f", tableName, amount, iban1, amount);
            int error1 = statement.executeUpdate(queryRemoveSoldFromUser1);

            if (error1 != 0) {
                Logs.log(String.format("The amount of %.2f has been successfully transferred from %s with IBAN: %s to %s with IBAN: %s", amount, nameUser1, iban1, nameUser2, iban2), "logsTransactions.txt");
                System.out.printf("%s successfully transferred the amount of %.2f to %s\n", nameUser1, amount, nameUser2);
            } else if (soldUser1 < amount && nameUser1 != "User not exist") {
                Logs.log(String.format("Transfer failed for %s with IBAN: %s because sold less then ammount.", nameUser1, iban1), "logsTransactions.txt");
            } else {
                Logs.log(String.format("Transfer failed because one of the user does not exist."), "logsTransactions.txt");
            }

            String queryAddSoldForUser2 = String.format("UPDATE %s SET sold = sold + %f WHERE iban = '%s'", tableName, amount, iban2);
            int error2 = statement.executeUpdate(queryAddSoldForUser2);

            if (error2 == 0) {
                Logs.log(String.format("Transfer failed. Account iban = %s not found.", iban2), "logsTransactions.txt");
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }
}