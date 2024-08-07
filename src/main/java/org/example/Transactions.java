package org.example;

import java.sql.*;

public class Transactions {

    Statement statement = null;

    public void addSold(Connection conn, String tableName, int id, double amount) {

        try {
            statement = conn.createStatement();
            String query = String.format("UPDATE %s SET sold = sold + %f WHERE id = %d", tableName, amount, id);

            int error = statement.executeUpdate(query);

            if (error == 0) {
                Logs.log(String.format("Deposit failed. Account ID = %d not found.", id), "logsTransactions.txt");
            } else {
                Logs.log(String.format("Deposit succes. For ID = %d.", id), "logsTransactions.txt");
            }

        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public void removeSold(Connection conn, String tableName, int id, double amount) {

        try {
            String query = String.format("UPDATE %s SET sold = sold - %f WHERE id = %d AND sold >= %f", tableName, amount, id, amount);
            statement = conn.createStatement();

            int error = statement.executeUpdate(query);

            if (error == 0) {
                Logs.log(String.format("Withdraw failed. Account ID = %d not found or Sold less then amount.", id), "logsTransactions.txt");
            } else {
                Logs.log(String.format("Withdraw succes. For ID = %d.", id), "logsTransactions.txt");
            }

        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public void transferSoldFromUser1toUser2(Connection conn, String tableName, int id1, int id2, double amount) {
        try {
            String queryRemoveSoldFromUser1 = String.format("UPDATE %s SET sold = sold - %f WHERE id = %d AND sold >= %f", tableName, amount, id1, amount);
            statement = conn.createStatement();

            int error1 = statement.executeUpdate(queryRemoveSoldFromUser1);

            if (error1 == 0) {
                Logs.log(String.format("Transfer failed. Account ID = %d not found or Sold less then amount.", id1), "logsTransactions.txt");
            } else {
                Logs.log(String.format("Transfer succes. From ID1 = %d to ID2 = %d.", id1, id2), "logsTransactions.txt");
            }

            String queryAddSoldForUser2 = String.format("UPDATE %s SET sold = sold + %f WHERE id = %d", tableName, amount, id1);
            statement = conn.createStatement();

            int error2 = statement.executeUpdate(queryAddSoldForUser2);

            if (error2 == 0) {
                Logs.log(String.format("Transfer failed. Account ID = %d not found.", id1), "logsTransactions.txt");
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }
}