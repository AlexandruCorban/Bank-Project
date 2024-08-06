package org.example;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        User.addUser(users, "Corban Alexandru", (short)1234, 5063.4);
        User.addUser(users, "Ioan Herbil", (short)5632, 1024.53);

        for(User user : users) {
            System.out.println(user);
        };


        }
    }