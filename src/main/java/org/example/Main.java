package org.example;

import java.sql.Connection;

public class Main {
    public static void main(String[] args){
        DataBase db = new DataBase();
        Transactions transactions = new Transactions();

        Login.loginDB("login.txt");

        Connection conn = db.getConnection(Login.dbName, Login.dbUsername, Login.dbPassword);


        // Functions test
//        db.deleteAllUsers(conn, "users");
//        db.generateUsers(conn, "users", 10000);
//        db.loadUsers(conn, "users");
//        db.addUser(conn, "users", db.generateIban(), "Corban Alexandru", 4564, 10626.43);
//        db.deleteUser(conn, "users", db.generateIban());
//        db.searchUserByName(conn, "users", "Corban Alexandru");
//
//        transactions.addSold(conn, "users", "RO56FTSBROBUFG1QRSIAK6JE", 373.57);
//        transactions.removeSold(conn, "users", "RO56FTSBROBUFG1QRSIAK6JE", 1000);
//        transactions.transferSoldFromUser1toUser2(conn, "users", "RO24FTSBROBUMLQATP1JYTDZ", "RO56FTSBROBUFG1QRSIAK6JE", 1000.0);
//
//        db.loadUsers(conn, "users");
   }
}