package org.taxiapp;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;

public class Customer extends User{

    // TODO: make customer fare and store their money teehee.
    // TODO: get taxis in location

    String username;
    String password;
    Location destination = new Location();
    double tip;
    double time;
    String region;
    String regionLocation;
    int x, y;
    String mapRegion;
    String mapLocation;
    int rating;
    Scanner input = new Scanner(System.in);
    String filePath = "src/main/java/org/taxiapp/Files/mapLocations.txt";
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


    public void signIn() throws NoSuchAlgorithmException {
        System.out.println("Welcome to GoGoTaxi service!, pick 1 for login or 2 to sign up: ");
        int answer = input.nextInt();
        if (answer == 1) {
            LoginManager.customerLogin();
        }
        else if (answer == 2) {
            LoginManager.customerSignUp();
        }
        username = LoginManager.getUsername();
    }
    public void insertDestination() {
        boolean enteredLocation = false;
        System.out.println("Enter region: \n-EVERGREEN \n-FROSTFIELD\n-SUNHAVEN\n-EMBERWOOD");
        input.nextLine();
            mapRegion = input.nextLine();
            locationGetter();
            System.out.println("Enter in your location: ");
            mapLocation = input.nextLine();
            if (mapLocation.equalsIgnoreCase(regionLocation)) {
                System.out.println("your coords: ");
                System.out.println(x + "," + y);
                destination.setX(x);
                destination.setY(y);
            }
    }

    public void locationGetter() {
        mapRegions mapregions = mapRegions.valueOf(mapRegion);
        String line;
        try(BufferedReader mapLocation = new BufferedReader(new FileReader(filePath))) {
            mapLocation.readLine();
            while ((line = mapLocation.readLine()) != null) {
                String[] data = line.split(", ");
                String region = data[0].trim();
                if (mapregions.name().equalsIgnoreCase(region)) {
                    regionLocation = data[1];
                    System.out.println(regionLocation);
                    x = Integer.parseInt(data[2]);
                    y = Integer.parseInt(data[3]);
                }
                //System.out.println(region + regionLocation + x + y);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getListOfTaxis(Customer customer) {
        int customerX = customer.location.getX();
        int customerY = customer.location.getY();

    }
    public void calculateTimeTaken() {

    }
    public void startJourney() {

    }
    public void tripExperience() {
        Random random = new Random();
        int randomExperience = random.nextInt(experience.length);
        System.out.println(experience[randomExperience]);
        System.out.println("Rate your experience out of 5: ");
        rating = input.nextInt();
        input.nextLine();
        System.out.print("you rated this trip: ");
        for (int i =0; i < rating; i++) {
            System.out.print("★");
        }
        System.out.println();
    }
    public void  tipTaxi() {
        System.out.println("Would you like to tip the driver?(yes/no): ");
        String answer = input.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            System.out.println("How much would you like to tip them?: ");
            tip = input.nextInt();
            input.nextLine();
            System.out.println("You tipped: " + tip + "\n Thankyou!");
        }

    }

    //testing purposes only.
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Customer c = new Customer();
//        c.signIn();
//        c.insertDestination()
        c.tripExperience();
//        c.tipTaxi();
    }
}
