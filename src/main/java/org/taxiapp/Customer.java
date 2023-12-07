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
    String[] experience = {
            "You call for a taxi, and a sleek, luxurious vehicle arrives promptly.\n The interior is immaculate, with plush leather seats and a courteous driver offering bottled water and snacks.\n The ride is smooth, the driver is knowledgeable about the city, and the overall experience feels premium and comfortable.",
            "You book a taxi through a service known for its exceptional customer service.\n The driver arrives early, greets you warmly, and assists with luggage.\n Throughout the journey, the driver engages in pleasant conversation, shares local tips, and ensures a safe and enjoyable ride. \n The service is prompt, and the vehicle is well-maintained.",
            "You hail a taxi on the street. The vehicle is clean, and the driver is polite.\n Although not overly chatty, the driver navigates efficiently through traffic and takes the most direct route.\n It's a standard, satisfactory ride without any major issues.",
            "You hop into a taxi and are greeted by a driver with a genuinely friendly demeanor.\n The driver strikes up a conversation, sharing interesting stories and local insights.\n Despite a slightly longer route due to traffic, the cheerful interaction makes the journey enjoyable.",
            "You book a taxi, but the driver arrives late, without any apology or explanation.\n The vehicle appears poorly maintained, and the driver is impolite and drives recklessly.\n The ride is uncomfortable and stressful, leaving you dissatisfied and wishing for a better experience."
    };

    public Customer() {
        location = new Location();
    }
    public void insertDestination() {}

    public void returnRegion(int i) {}

    public void locationGetter() {}
    public void coordinateGetter() {}


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

    //testing purposes only.
    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException {
        Customer c = new CustomerLocation();
//        c.signIn();
        c.insertDestination();
//        c.tripExperience();
//        c.tipTaxi();


    }
}
