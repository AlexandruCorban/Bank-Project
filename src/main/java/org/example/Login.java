package org.example;

import java.io.*;
import java.util.Scanner;

public class Login {
    static String dbName;
    static String dbUsername;
    static String dbPassword;

    public static void loginDB(String fileName) {
        try {
            File file = new File(fileName);
            boolean exists = file.exists();

            if (!exists) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
                Scanner sc = new Scanner(System.in);

                System.out.print("Enter your database username: ");
                dbName = sc.nextLine();
                writer.write(dbName + "\n");

                System.out.print("Enter your username: ");
                dbUsername = sc.nextLine();
                writer.write(dbUsername + "\n");

                System.out.print("Enter your password: ");
                dbPassword = sc.nextLine();
                writer.write(dbPassword + "\n");

                writer.close();
                sc.close();
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                dbName = reader.readLine();
                dbUsername = reader.readLine();
                dbPassword = reader.readLine();
            }
        } catch (Exception e) {
            Logs.log(e.getMessage(), "logs.txt");
        }
    }
}