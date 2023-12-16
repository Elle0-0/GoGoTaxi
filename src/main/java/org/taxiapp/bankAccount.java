package org.taxiapp;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class bankAccount {
    static double balance;
    static double funds;
    static String filePath = "src/main/java/org/taxiapp/resources/userData.csv";

    public static void updateFunds(Customer customer, double newFunds) throws FileNotFoundException {
        String line;
        try (BufferedReader amount = new BufferedReader(new FileReader(filePath))) {
            amount.readLine();
            ArrayList<String> money = new ArrayList<>();
            while ((line = amount.readLine()) != null) {
                String[] data = line.split(",");
                //funds = Double.parseDouble(data[3]);
                String username=data[0];
                if (customer.getUsername().equals(username)) {
                    data[data.length - 1] += "," + newFunds;
                    line = String.join(",", data);
                }
                money.add(line);
            }
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String customerInfo : money) writer.write(customerInfo + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static double calculateFunds(Customer customer) throws FileNotFoundException {
        String line;
        double sum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String username =data[0];
                if (customer.getUsername().equals(username)) {
                    for (int i = 2; i < data.length; i++) {
                        sum += Double.parseDouble(data[i]);
                    }
                }
            }
            return sum;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        Customer c = new Customer();
        c.signIn();
        //updateFunds(c, 30);
        System.out.println(calculateFunds(c));
    }
}
