package org.taxiapp;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankAccount {

    /** The bank account class that handles adding and calculating the user funds. the methods
     * are static with customer references.*/

    static String filePath = "src/main/java/org/taxiapp/resources/userData.csv";
    static Scanner input = new Scanner(System.in);

    // prompts the user to add funds into their bank account at the start of the application after signing in.
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
                if (customerFunding > 500 || customerFunding < 1 ) {        //user cannot add more than 500
                    System.out.println("max amount you can add is 100. Do not enter negative values.");
                    continue;
                }
                updateFunds(customer, customerFunding);
                System.out.println("Thank you for updating ur account, ur new balance is: " + calculateFunds(customer));
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("invalid input.");
                input.nextLine();
            }
        }
    }

    //adds the new funds and then rewrites the file.
    public static void updateFunds(Customer customer, double newFunds) {
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

    // Calculates the total funds the user has.
    public static double calculateFunds(Customer customer) {
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

    // calculates funds in back account, if they are not broke, asks the user if they want to tip.
    // adds the negative value to the file so it is subtracted from the total.
    public static void tipTaxi(Customer customer) {
        boolean validInput = false;
        double balance = calculateFunds(customer);
        System.out.println(balance);
        if (balance > 0 ) {
            while (!validInput) {
                try {
                    System.out.println("Would you like to tip the driver?\n[1] yes\n[2] no");
                    int answer = input.nextInt();
                    if (!(answer == 1) && !(answer == 2)) continue;
                    if (answer == 2) {
                        System.out.println("Thank you for using GoGoTaxi!");
                        break;
                    }
                    else {
                        System.out.println("How much would you like to tip them?: ");
                        customer.tip = input.nextDouble();
                        input.nextLine();
                        if (customer.tip < balance && customer.tip > -1) {
                            BankAccount.updateFunds(customer, Double.parseDouble("-" + customer.tip));
                            System.out.println("You tipped: " + customer.tip + "\n Thank you!");
                        }
                        else if (customer.tip > balance) {
                            System.out.println("You do not have enough money in ur account. try again.");
                            continue;
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Enter valid input.");
                    input.nextLine();
                    continue;
                }
                validInput = true;
            }
        }
    }
}
