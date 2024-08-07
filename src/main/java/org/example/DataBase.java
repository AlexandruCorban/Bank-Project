package org.example;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.*;
import java.util.Random;

public class DataBase {

    Statement statement = null;
    ResultSet rs = null;

    public Connection getConnection(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null) {
                Logs.log("Connected to PostgreSQL database", "logs.txt");
            } else {
                Logs.log("Failed to connect to PostgreSQL database", "logs.txt");
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
        return conn;
    }

    public void loadUsers(Connection conn, String tableName) {
        try {
            String query = String.format("SELECT * FROM %s", tableName);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getString("iban") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.print(rs.getShort("pin") + " ");
                System.out.println(rs.getDouble("sold") + " ");
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public void generateUsers(Connection conn, String tableName, int size) {
        String[] names = {
                "Andrei", "Ana", "Ion", "Maria", "George", "Elena", "Mihai", "Ioana", "Paul", "Roxana",
                "Alexandru", "Gabriela", "Daniel", "Laura", "Cristian", "Simona", "Florin", "Carmen", "Stefan", "Adriana",
                "Radu", "Iulia", "Vlad", "Nicoleta", "Sorin", "Madalina", "Marius", "Elena", "Diana", "Ilie",
                "Nicolae", "Raluca", "Teodor", "Georgiana", "Victor", "Bianca", "Liviu", "Roxana", "Cosmin", "Marian"
        };
        String[] surnames = {
                "Popescu", "Ionescu", "Georgescu", "Dumitrescu", "Stan", "Radu", "Marin", "Munteanu", "Petrescu", "Badea",
                "Pavel", "Dima", "Carstea", "Tudor", "Sava", "Balan", "Mihaila", "Gheorghe", "Rusu", "Andrei",
                "Cojocaru", "Dragomir", "Nicu", "Lungu", "Voinea", "Sorin", "Vasile", "Lazar", "Vancea", "Popa"
        };

        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            String iban = generateIban();
            String name = names[rand.nextInt(names.length)];
            String surname = surnames[rand.nextInt(surnames.length)];
            String fullName = name + " " + surname;
            short pin = (short)(1000 + rand.nextInt(9000));
            double sold = rand.nextDouble() * 10000;

            addUser(conn, tableName, iban, fullName, pin, sold);
        }

    }

    public void addUser(Connection conn, String tableName, String iban, String name, int pin, double sold) {
        try {
            String query = String.format("INSERT INTO %s(iban, name, pin, sold) VALUES('%s','%s','%d','%f')",tableName, iban, name, pin, sold);
            statement = conn.createStatement();
            statement.executeUpdate(query);

            Logs.log(String.format("User %s added to table %s.", name, tableName), "logsDB.txt");
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public void deleteAllUsers(Connection conn, String tableName) {
        try {
            String query = String.format("DELETE FROM %s", tableName);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            Logs.log(String.format("All users from table %s have been deleted.", tableName), "logsDB.txt");
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public void deleteUser(Connection conn, String tableName, String iban) {
        try {
            String query = String.format("DELETE FROM %s WHERE iban = '%s'", tableName, iban);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            Logs.log(String.format("User with iban %s deleted from table %s.", iban, tableName), "logsDB.txt");
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public void searchUserByName(Connection conn, String tableName, String name) {
        try {
            String query = String.format("SELECT * FROM %s WHERE name = '%s'", tableName, name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                String iban = rs.getString("iban");
                String fullname = rs.getString("name");
                short pin = rs.getShort("pin");
                double sold = rs.getDouble("sold");

                System.out.printf("%s %s %d %.2f%n", iban, fullname, pin, sold);
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }

    public String generateIban () {
        Random rand = new Random();

        String[] bankcodes = {
                "BTRLRO22", "RNCBROBU", "IVVIROB2", "FTSBROBU", "BCRLROBU", "BKCHROBU"
        };
        int firstDigitis = (10 + rand.nextInt(90));
        String bankcode = bankcodes[rand.nextInt(bankcodes.length)];
        String accountNumber =  RandomStringUtils.random(12, true, true);

        String iban = "RO" + firstDigitis + bankcode + accountNumber;
        iban = iban.toUpperCase();

        return iban;
    }
}
