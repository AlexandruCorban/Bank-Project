package org.example;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private short pin;
    private double sold;

    public User(String name, short pin, double sold) {
        this.name = name;
        this.pin = pin;
        this.sold = sold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getPin() {
        return pin;
    }

    public void setPin(short pin) {
        this.pin = pin;
    }

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    public static void addUser(List<User> users, String name, short pin, double sold) {
        User newUser = new User(name, pin, sold);
        users.add(newUser);
    }

    @Override
    public String toString() {
        return "User {" +
                "Name='" + name + '\'' +
                ", Pin=" + pin +
                ", Sold= " +  sold +
                '}';
    }
}
