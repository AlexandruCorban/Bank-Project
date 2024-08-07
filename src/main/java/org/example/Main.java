package org.example;

import java.sql.Connection;

public class Main {
    public static void main(String[] args){
        DataBase db = new DataBase();
        Transactions transactions = new Transactions();

        Login.loginDB("login.txt");

        Connection conn = db.getConnection(Login.dbName, Login.dbUsername, Login.dbPassword);


        // Functions test
        db.deleteAllUsers(conn, "users");
        db.generateUsers(conn, "users", 1000);
        db.loadUsers(conn, "users");
        db.addUser(conn, "users", "Corban Alexandru", 4564, 10626.43);
        db.deleteUser(conn, "users", 1000);
        db.searchUserByName(conn, "users", "Corban Alexandru");

        transactions.addSold(conn, "users", 1001, 373.57);
        transactions.removeSold(conn, "users", 1001, 1000);
        transactions.transferSoldFromUser1toUser2(conn, "users", 999, 1000, 1000.0);
    }
}