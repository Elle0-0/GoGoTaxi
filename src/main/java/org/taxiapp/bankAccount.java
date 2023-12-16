package org.taxiapp;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class bankAccount {
    static double sum = 0;
    static String filePath = "src/main/java/org/taxiapp/resources/userData.csv";
    static Scanner input = new Scanner(System.in);

    public static void addFunds(Customer customer) throws FileNotFoundException {
        System.out.println("Welcome " + customer.getUsername() + " would you like to add funds to your bank account,\n remaining balance is: " + calculateFunds(customer));
        System.out.println("[1] yes \n[2] no");
        boolean validInput = false;
        while (!validInput) {
            try {
                int answer = input.nextInt();
                if (answer == 2) break;
                if (!(answer == 1)) {
                    System.out.println("pick 1 or 2.");
                    continue;
                }
                System.out.println("How much would u like to add?: ");
                double customerFunding = input.nextDouble();
                if (customerFunding > 100 || customerFunding < 1 ) {
                    System.out.println("max amount you can add is 100. Do not enter negative values.");
                    continue;
                }
                updateFunds(customer, customerFunding);
                System.out.println("Thankyou for updating ur account, ur new balance is: " + calculateFunds(customer));
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("invalid input.");
                input.nextLine();
            }
        }
    }

    public static void updateFunds(Customer customer, double newFunds) throws FileNotFoundException {
        String line;
        try (BufferedReader amount = new BufferedReader(new FileReader(filePath))) {
            ArrayList<String> money = new ArrayList<>();
            while ((line = amount.readLine()) != null) {
                String[] data = line.split(",");
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
}
