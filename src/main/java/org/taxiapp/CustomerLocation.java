package org.taxiapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerLocation extends Customer {
    Scanner input = new Scanner(System.in);
    String filePath = "src/main/java/org/taxiapp/Files/mapLocations.txt";
    boolean checker = false;
    int temp;
    Coordinates coordinates = new Coordinates();
    ArrayList<String> enteredLocations = new ArrayList<>();

    public CustomerLocation() throws IOException {
    }

    @Override
    public void insertDestination(Location location) {
        boolean enteredLocation = false;
        int userInput;
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
                    if (userLocationInput > enteredLocations.size()) continue;
                    mapLocation = enteredLocations.get(userLocationInput - 1);
                    int [] coords = coordinates.retrieveCoordinates(returnRegion(userInput), mapLocation);
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

    @Override
    public void getCustomerLocation() {
        System.out.println("---------Where are you currently?---------");
        insertDestination(location);
        System.out.println("Thank you for entering in your location!\n\n");
    }

    @Override
    public void getCustomerDestination() {
        System.out.println("---------Where do you want to go?---------");
        insertDestination(destination);
    }

    @Override
    public mapRegions returnRegion(int i) {
        if (i == 1) regions = mapRegions.EVERGREEN;
        else if (i == 2) regions = mapRegions.SUNHAVEN;
        else if (i == 3) regions = mapRegions.FROSTFIELD;
        else if (i == 4) regions = mapRegions.EMBERWOOD;
        return regions;
    }

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
                    //System.out.println(enteredLocations);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws IOException {
        Customer c = new CustomerLocation();
        c.getCustomerLocation();
        System.out.println(c.location.getX());
        System.out.println(c.location.getY());
        c.getCustomerDestination();
        System.out.println(c.destination.getX());
        System.out.println(c.destination.getY());
    }
}
