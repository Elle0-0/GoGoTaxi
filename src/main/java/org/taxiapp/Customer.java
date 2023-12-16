package org.taxiapp;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Customer extends User{

    String username;
    Location destination;
    double tip;
    String regionLocation;
    mapRegions regions;
    String mapLocation;
    int rating;
    double funds;
    ArrayList<String> customerExperience = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    String experienceFilePath = "src/main/java/org/taxiapp/resources/experience.csv";

    public Customer() throws IOException {
        location = new Location();
        destination = new Location();
    }
    public void insertDestination(Location location) {}

    public mapRegions returnRegion(int i) {
        return null;
    }
    public void locationGetter() {}
    public void getCustomerLocation() {}
    public void getCustomerDestination() {}
    public void getExprience() throws FileNotFoundException {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(experienceFilePath))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                for (String column : data) {
                    String lineBreak = column.replace("\\n", "\n");
                    customerExperience.add(lineBreak);}

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void signIn() throws NoSuchAlgorithmException {
        System.out.println("Welcome to GoGoTaxi service!, please select an option:\n [1] login\n [2] sign up: ");
        boolean validInput = false;
        while (!validInput) {
            try {
                int answer = input.nextInt();
                if (answer == 1) {
                    LoginManager.customerLogin();
                } else if (answer == 2) {
                    LoginManager.customerSignUp();
                } else {
                    continue;
                }
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("pick 1 or 2");
                input.nextLine();
            }
        }
        username = LoginManager.getUsername();
    }
    public void calculateTimeTaken(VehicleHiring vehicleHiring) {
        //double distanceTravelled = vehicleHiring.worldMap.getDistanceTravelled();
        //System.out.println("Your journey took " + (distanceTravelled*1.2) + "minutes.");
        //System.out.println("And you travelled " + distanceTravelled + "kilometers");
    }

    public void tripExperience() throws FileNotFoundException {
        getExprience();
        Random random = new Random();
        int randomExperience = random.nextInt(customerExperience.size());
        System.out.println(customerExperience.get(randomExperience));
        boolean inputRating = false;
        while (!inputRating) {
            try {
                System.out.println("Rate your experience out of 5: ");
                rating = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("enter valid rating.");
                input.nextLine();
                continue;
            }
            if (rating > 5) continue;
            inputRating = true;
            System.out.print("you rated this trip: ");
            for (int i = 0; i < rating; i++) {
                System.out.print("★");
            }
            System.out.println();
        }
    }
    public int getRating() {
        return rating;
    }
    public String getUsername() {
        return username;
    }

    public double getFunds() {
        return funds;
    }
    public void  tipTaxi(Customer customer) throws FileNotFoundException {
        boolean validInput = false;
        double balance = bankAccount.calculateFunds(customer);
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
                        tip = input.nextInt();
                        input.nextLine();
                        if (tip <= balance) {
                            bankAccount.updateFunds(customer, Double.parseDouble("-" + tip));
                            System.out.println("You tipped: " + tip + "\n Thankyou!");
                        }
                        else if (tip > balance) {
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
    //testing purposes only.
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        Customer c = new CustomerLocation();
//        c.signIn();
//        c.insertDestination();
//        c.tripExperience();
//        c.tipTaxi();


    }
}
