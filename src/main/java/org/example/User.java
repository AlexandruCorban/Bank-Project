package org.example;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private short pin;
    private double sold;

    public User(int id, String name, short pin, double sold) {
        this.id = id;
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

    public void setId(int id) {
        this.id = id;
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

    public static void loadUser() {
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
