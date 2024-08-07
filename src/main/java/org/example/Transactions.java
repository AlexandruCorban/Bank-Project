package org.example;

import java.sql.*;

public class Transactions {

    Statement statement = null;

    public void addSold(Connection conn, String tableName, String iban, double amount) {

        try {
            statement = conn.createStatement();
            String query = String.format("UPDATE %s SET sold = sold + %f WHERE iban = '%s'", tableName, amount, iban);

            int error = statement.executeUpdate(query);

            if (error == 0) {
                Logs.log(String.format("Deposit failed. Account iban = %s not found.", iban), "logsTransactions.txt");
            } else {
                Logs.log(String.format("Deposit succes. For iban = %s.", iban), "logsTransactions.txt");
            }

        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public void removeSold(Connection conn, String tableName, String iban, double amount) {

        try {
            String query = String.format("UPDATE %s SET sold = sold - %f WHERE iban = '%s' AND sold >= %f", tableName, amount, iban, amount);
            statement = conn.createStatement();

            int error = statement.executeUpdate(query);

            if (error == 0) {
                Logs.log(String.format("Withdraw failed. Account iban = %s not found or Sold less then amount.", iban), "logsTransactions.txt");
            } else {
                Logs.log(String.format("Withdraw succes. For iban = %s.", iban), "logsTransactions.txt");
            }

        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public void transferSoldFromUser1toUser2(Connection conn, String tableName, String iban1, String iban2, double amount) {
        try {
            String queryRemoveSoldFromUser1 = String.format("UPDATE %s SET sold = sold - %f WHERE iban = '%s' AND sold >= %f", tableName, amount, iban1, amount);
            statement = conn.createStatement();

            int error1 = statement.executeUpdate(queryRemoveSoldFromUser1);

            if (error1 == 0) {
                Logs.log(String.format("Transfer failed. Account iban = %s not found or Sold less then amount.", iban1), "logsTransactions.txt");
            } else {
                Logs.log(String.format("Transfer succes. From iban1 = %s to iban2 = %s.", iban1, iban2), "logsTransactions.txt");
            }

            String queryAddSoldForUser2 = String.format("UPDATE %s SET sold = sold + %f WHERE iban = '%s'", tableName, amount, iban2);
            statement = conn.createStatement();

            int error2 = statement.executeUpdate(queryAddSoldForUser2);

            if (error2 == 0) {
                Logs.log(String.format("Transfer failed. Account iban = %s not found.", iban2), "logsTransactions.txt");
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }
}