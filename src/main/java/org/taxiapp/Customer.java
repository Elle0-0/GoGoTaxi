package org.taxiapp;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Customer extends User{


    String username;
    Location destination = new Location();
    //Location currentLocation = new Location();
    double tip;
    double time;
    String region;
    String regionLocation;
    int x, y;
    mapRegions regions;
    String mapLocation;
    int rating;
    VehicleHiring vehicleHiring = new VehicleHiring();
    ArrayList<String> customerExperience = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    String filePath = "src/main/java/org/taxiapp/Files/mapLocations.txt";
    String experienceFilePath = "src/main/java/org/taxiapp/resources/experience.csv";

    public Customer() {
        location = new Location();
    }
    public void insertDestination(Location location) {}

    public void returnRegion(int i) {}

    public void locationGetter() {}
    public void coordinateGetter() {}
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
        double distanceTravelled = vehicleHiring.worldMap.getDistanceTravelled();
        System.out.println("Your journey took " + (distanceTravelled*1.2) + "minutes.");
        System.out.println("And you travelled " + distanceTravelled + "kilometers");

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
    public void  tipTaxi() {
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Would you like to tip the driver?(yes/no): ");
                String answer = input.nextLine();
                if (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) continue;
                if (answer.equalsIgnoreCase("yes")) {
                    System.out.println("How much would you like to tip them?: ");
                    tip = input.nextInt();
                    input.nextLine();
                    System.out.println("You tipped: " + tip + "\n Thankyou!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter valid input.");
                input.nextLine();
                continue;
            }
            validInput = true;
        }
    }

    public void getTaxi() {

    }

    //testing purposes only.
    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException {
        Customer c = new CustomerLocation();
//        c.signIn();
//        c.insertDestination();
//        c.tripExperience();
//        c.tipTaxi();


    }
}
