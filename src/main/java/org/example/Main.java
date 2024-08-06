package org.example;

import java.sql.Connection;


public class Main {
    public static void main(String[] args) {
        DataBase db = new DataBase();

       Connection con = db.getConnection("UsersDB", "postgres", "12345");

        db.addUser(con, "users","Teodoro Emil", (short)4534, 12045.65);

        }
    }