package org.example;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
       DataBase db = new DataBase();
       Transactions transactions = new Transactions();
       Search search = new Search();

       Login.loginDB("login.txt");

//     Functions test
       db.deleteAllUsers("users");
       db.generateUsers("users", 1000);
       db.loadUsers("users");

       try (Connection conn = db.getConnection(Login.dbName, Login.dbUsername, Login.dbPassword)) {
           db.addUser(conn, "users", db.generateIban(), "Corban Alexandru", db.generatePhoneNumber(), 4524, 10626.43);
       } catch (Exception e) {}

       db.deleteUser("users", "RO27PROBROBU478420207075");


       transactions.addSold("users", "RO48BRPROFBU894268725884", 374.00);
       transactions.removeSold("users", "RO48BRPROFBU894268725884 ", 100.43);
       transactions.transferSoldFromUser1toUser2("users", "RO48BRPROFBU894268725884", "RO62INGBROBU206980703262", 1000.0);

       search.filterDescSold("users", 100);
       search.filterAscSold("users", 100);
       search.searchUserByName("users", "Corban Alexandru");
       search.searchUserByIban("users", "RO62INGBROBU206980703262");
       search.searchUserByPhoneNumber("users", "07717333891");
   }
}