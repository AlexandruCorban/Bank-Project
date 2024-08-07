package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logs {
    public static void log(String message, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

            writer.write("[" + date + "] " + message + "\r\n");
            writer.close();
        } catch (Exception e) {
            log(e.getMessage(), "logs.txt");
        }
    }
}