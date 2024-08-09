package org.example;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DataBase db = new DataBase();
        Transactions transactions = new Transactions();
        Search search = new Search();

        Login.loginDB("login.txt");

        Connection conn = db.getConnection(Login.dbName, Login.dbUsername, Login.dbPassword);


//        Functions test
       db.deleteAllUsers(conn, "users");
       db.generateUsers(conn, "users", 1000);
       db.loadUsers(conn, "users");
       db.addUser(conn, "users", db.generateIban(), "Corban Alexandru", db.generatePhoneNumber(), 4524,10626.43);
       db.deleteUser(conn, "users", "RO74BSTRROBU008803320682");


        transactions.addSold(conn, "users", "RO91BSTRROBU825197795078", 460.00);
        transactions.removeSold(conn, "users", "RO89CETRROBU325210613219", 218);
        transactions.transferSoldFromUser1toUser2(conn, "users", "RO48TEBPROBU599222014478", "RO51PIRBROBU090403549749", 1000.0);

        db.loadUsers(conn, "users");


       search.filterDescSold(conn, "users", 100);
       search.filterAscSold(conn, "users", 100);
       search.searchUserByName(conn, "users", "Corban Alexandru");
       search.searchUserByIban(conn, "users", "RO89CETRROBU325210613219");
       search.searchUserByPhoneNumber(conn, "users", "07754509080");

    }
}