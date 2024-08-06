package org.example;

import java.sql.Connection;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        DataBase db = new DataBase();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the password for Database: ");
        String Password;
        Password = sc.nextLine();

        Connection conn = db.getConnection("UsersDB", "postgres", Password);

        db.loadUser(conn, "users");
        db.searchUserByName(conn, "users", "Corban Alexandru");

        }
    }