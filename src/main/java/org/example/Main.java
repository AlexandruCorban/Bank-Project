package org.example;

import java.sql.Connection;

public class Main {
    public static void main(String[] args){
        DataBase db = new DataBase();
        Transactions transactions = new Transactions();
        Search search = new Search();

        Login.loginDB("login.txt");

        Connection conn = db.getConnection(Login.dbName, Login.dbUsername, Login.dbPassword);


        // Functions test
        db.deleteAllUsers(conn, "users");
        db.generateUsers(conn, "users", 100000);
        db.loadUsers(conn, "users");
        db.addUser(conn, "users", db.generateIban(), "Corban Alexandru", db.generatePhoneNumber(), 4524,10626.43);
        db.deleteUser(conn, "users", db.generateIban());


        transactions.addSold(conn, "users", "RO56FTSBROBUFG1QRSIAK6JE", 373.57);
        transactions.removeSold(conn, "users", "RO56FTSBROBUFG1QRSIAK6JE", 1000);
        transactions.transferSoldFromUser1toUser2(conn, "users", "RO24FTSBROBUMLQATP1JYTDZ", "RO56FTSBROBUFG1QRSIAK6JE", 1000.0);

        db.loadUsers(conn, "users");

       search.filterDescSold(conn, "users", 100);
       search.filterAscSold(conn, "users", 100);
       search.searchUserByName(conn, "users", "Corban Alexandru");
       search.searchUserByIban(conn, "users", "RO83FTSBROBU297388583885");
       search.searchUserByPhoneNumber(conn, "users", "07364164818");
   }
}