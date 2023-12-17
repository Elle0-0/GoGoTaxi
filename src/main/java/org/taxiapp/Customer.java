package org.taxiapp;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Customer extends User{

    /** The main customer class, it is extended by the CustomerLocation class. Handles all
     * input prompts and stores coordinates of the customer.*/

    String username;
    Location destination;
    double tip;
    String regionLocation;
    mapRegions regions;
    String mapLocation;
    int rating;
    ArrayList<String> customerExperience = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    String experienceFilePath = "src/main/java/org/taxiapp/resources/experience.csv";

    public Customer() {
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

    //randomly generates a user experience from the csv file and displays it to the user.
    public void getExperience() {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(experienceFilePath))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                for (String column : data) {
                    String lineBreak = column.replace("\\n", "\n");
                    customerExperience.add(lineBreak);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //prompts the user to pick between sign up or login and then calls those functions from login manager.
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

    //calls the getExperience() function then prompts the user to rate their experience out of 5.
    public void tripExperience() {
        getExperience();
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

}
