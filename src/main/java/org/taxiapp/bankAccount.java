package org.taxiapp;

import java.io.*;

public class bankAccount {
    static double balance;
    static double funds;
    static String filePath = "src/main/java/org/taxiapp/resources/userData.csv";

    public static void addFunds(double funds) {
        try (BufferedWriter amount = new BufferedWriter(new FileWriter(filePath, true))) {
            amount.append((char)funds);
            amount.append(",");
            amount.newLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void  updateFunds(double newFunds) throws FileNotFoundException {
        String line;
        try (BufferedReader amount = new BufferedReader(new FileReader(filePath))) {
            amount.readLine();
            while ((line = amount.readLine()) != null) {
                String[] data = line.split(",");
                funds = Double.parseDouble(data[3]);
            }
            funds += newFunds;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
