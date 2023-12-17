package org.taxiapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerLocation extends Customer {

    /** CustomerLocation extends Customer and overrides the location methods
     * ive done this to maintain OOP and reduce the lines of code in a singular class. */
    Scanner input = new Scanner(System.in);
    String filePath = "src/main/java/org/taxiapp/Files/mapLocations.txt";
    ArrayList<String> enteredLocations = new ArrayList<>();
    int userInput;
    public CustomerLocation() throws IOException {
    }

    // Gets the region and then its location and retrieves the coordinates for it.
    @Override
    public void insertDestination(Location location) {
        boolean enteredLocation = false;
        while (!enteredLocation) {
            try {
                System.out.println("Enter region: \n[1] for EVERGREEN \n[2] for SUNHAVEN\n[3] for FROSTFIELD\n[4] for EMBERWOOD");
                userInput = input.nextInt();
                input.nextLine();
                if (userInput >= 1 && userInput <= 4) {
                    returnRegion(userInput);
                    locationGetter();
                    System.out.println("Enter in your location: ");
                    int userLocationInput = input.nextInt();
                    if (userLocationInput > enteredLocations.size()) {System.out.println("--please pick a location from the given options--"); continue;};
                    mapLocation = enteredLocations.get(userLocationInput - 1);
                    int [] coords = Coordinates.retrieveCoordinates(returnRegion(userInput), mapLocation);
                    if (coords[0] != 0 && coords[1] != 0) {
                        location.setX(coords[0]);
                        location.setY(coords[1]);
                        enteredLocation = true;
                    }else continue;
                } else continue;
            } catch (InputMismatchException e) {
                System.out.println("enter region from the given options: ");
                input.nextLine();
            }
        }
    }

    // prompts the customer to enter in their location and calls insertDestination() method.
    @Override
    public void getCustomerLocation() {
        System.out.println("---------Where are you currently?---------");
        insertDestination(location);
        System.out.println("Thank you for entering in your location!\n\n");
    }

    // prompts the customer to enter in their destination (where they want to go using the taxi service)
    // and calls insertDestination() method.
    @Override
    public void getCustomerDestination() {
        System.out.println("---------Where do you want to go?---------");
        insertDestination(destination);
    }

    //returns the region enum.
    @Override
    public mapRegions returnRegion(int i) {
        if (i == 1) regions = mapRegions.EVERGREEN;
        else if (i == 2) regions = mapRegions.SUNHAVEN;
        else if (i == 3) regions = mapRegions.FROSTFIELD;
        else if (i == 4) regions = mapRegions.EMBERWOOD;
        else return null;
        return regions;
    }

    //gets the location after user enters their region.
    @Override
    public void locationGetter() {
        String line;
        int i = 1;
        enteredLocations.clear();
        try(BufferedReader mapLocation = new BufferedReader(new FileReader(filePath))) {
            mapLocation.readLine();
            while ((line = mapLocation.readLine()) != null) {
                String[] data = line.split(", ");
                mapRegions region = mapRegions.valueOf(data[0]);
                if (regions.equals(region)) {
                    enteredLocations.add(data[1].trim());
                    regionLocation = data[1].trim();
                    System.out.print(" ["+i+"] ");
                    System.out.println(regionLocation);
                    i++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
