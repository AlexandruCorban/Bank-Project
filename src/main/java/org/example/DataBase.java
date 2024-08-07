package org.example;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


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
                System.out.print(rs.getString("phonenumber") + " ");
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
                "Nicolae", "Raluca", "Teodor", "Georgiana", "Victor", "Bianca", "Liviu", "Roxana", "Cosmin", "Marian",
                "Alin", "Claudia", "Claudiu", "Daria", "Eusebiu", "Felicia", "Gabriel", "Horia", "Ionut", "Julia",
                "Lavinia", "Mihnea", "Narcis", "Oana", "Petru", "Ramona", "Sabina", "Traian", "Tudor", "Valentina",
                "Alexandrina", "Bogdan", "Cezar", "Delia", "Eduard", "Flavius", "Grigore", "Hannelore", "Irina", "Janina",
                "Kevin", "Liana", "Manuela", "Narcisa", "Octavian", "Patricia", "Raul", "Sorina", "Teodora", "Viorica",
                "Xenia", "Yvonne", "Zoe", "Adrian", "Beatrice", "Costin", "Doina", "Emilian", "Fabia", "George", "Elvis",
                "Horatiu", "Isabela", "Joachim", "Laurentiu", "Mircea", "Nora", "Olimpia", "Rares", "Sergiu", "Tiberiu"
        };
        String[] surnames = {
                "Popescu", "Ionescu", "Georgescu", "Dumitrescu", "Stan", "Radu", "Marin", "Munteanu", "Petrescu", "Badea",
                "Pavel", "Dima", "Carstea", "Tudor", "Sava", "Balan", "Mihaila", "Gheorghe", "Rusu", "Andrei",
                "Cojocaru", "Dragomir", "Nicu", "Lungu", "Voinea", "Sorin", "Vasile", "Lazar", "Vancea", "Popa",
                "Barbu", "Apostol", "Corban", "Florescu", "Grigorescu", "Hagi", "Iacob", "Jianu", "Luca", "Matei",
                "Neacsu", "Oprea", "Pavelescu", "Radulescu", "Stoian", "Toma", "Ursu", "Vlad", "Zaharia", "Anghel",
                "Badescu", "Chiru", "Diaconu", "Murat", "Florea", "Gabor", "Harabagiu", "Ilie", "Jitaru", "Lupescu",
                "Manole", "Nastase", "Olaru", "Parvu", "Rauta", "Sandu", "Tautu", "Uta", "Visan", "Zamfir",
                "Borza", "Calin", "Dobre", "Eftimie", "Faur", "Ghita", "Hopartean", "Iordache", "Juganaru", "Lacatus",
                "Mitrea", "Nedelcu", "Opris", "Popa", "Roscoveanu", "Stanescu", "Trandafir", "Ureche", "Varlan", "Zaharia",
                "Botez", "Catanescu", "Dican", "Enescu", "Farcas", "Grosu", "Hritcu", "Iliescu", "Juganaru", "Lupu"
        };

        Random rand = new Random();
        Set<String> phoneNumbers = new HashSet<>();
        Set<String> ibans = new HashSet<>();

        for (int i = 0; i < size; i++) {
            String iban;

            do {
                iban = generateIban();
            } while (!ibans.add(iban));

            String name = names[rand.nextInt(names.length)];
            String surname = surnames[rand.nextInt(surnames.length)];
            String fullName = name + " " + surname;
            String phoneNumber;

            do {
                phoneNumber = generatePhoneNumber();
            } while (!phoneNumbers.add(phoneNumber));

            short pin = (short)(1000 + rand.nextInt(9000));
            double sold = rand.nextDouble() * 10000;

            addUser(conn, tableName, iban, fullName, phoneNumber, pin, sold);
        }
    }

    public void addUser(Connection conn, String tableName, String iban, String name, String phoneNumber, int pin, double sold) {
        try {
            String query = String.format("INSERT INTO %s(iban, name, phonenumber, pin, sold) VALUES('%s','%s','%s', '%d','%f')",tableName, iban, name, phoneNumber, pin, sold);
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

    public String generateIban () {
        Random rand = new Random();

        String[] bankCodes = {
                "BTRLRO22", "RNCBROBU", "INGBROBU", "FTSBROBU", "BCRLROBU",
                "BRDOROBU", "BSTRROBU", "UNICROBU", "EXCRROBU", "PROBROBU",
                "ALFABRRO", "CETRROBU", "TEBPROBU", "VBRDROBU", "RBRDROBU",
                "MIBLROBU", "ABNARO22", "PIRBROBU", "BCLBROBU", "BRPROFBU"
        };
        int firstDigitis = (10 + rand.nextInt(90));
        String bankcode = bankCodes[rand.nextInt(bankCodes.length)];
        String accountNumber =  RandomStringUtils.random(12, false, true);

        String iban = "RO" + firstDigitis + bankcode + accountNumber;
        iban = iban.toUpperCase();

        return iban;
    }

    public String generatePhoneNumber () {
        Random rand = new Random();
        String phoneNumber;

        int[] prefixs = {
                // Orange
                740, 741, 742, 743, 744, 745, 746, 747, 748, 749,
                750, 751, 752, 753, 754, 755, 756, 757, 758, 759,
                // Vodafone
                720, 721, 722, 723, 724, 725, 726, 727, 728, 729,
                730, 731, 732, 733, 734, 735, 736, 737, 738, 739,
                372, 371, 799,
                // Telekom
                760, 761, 762, 763, 764, 765, 766, 767, 768, 769,
                786,
                // Digi Mobil
                770, 771, 772, 773, 774, 775, 776, 777, 778, 779
        };
        int prefix = prefixs[rand.nextInt(prefixs.length)];
        int localNumber = rand.nextInt(10000000);
        phoneNumber = "0" + prefix + String.format("%07d", localNumber);

        return phoneNumber;
    }
}
