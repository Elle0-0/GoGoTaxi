package org.taxiapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerLocation extends Customer {
    Scanner input = new Scanner(System.in);
    String filePath = "src/main/java/org/taxiapp/Files/mapLocations.txt";
    boolean checker = false;

    @Override
    public void insertDestination() {
        boolean enteredLocation = false;
        int userInput;
        while (!enteredLocation) {
            try {
                System.out.println("Enter region: \n-1 for EVERGREEN \n-2 for SUNHAVEN\n-3 for FROSTFIELD\n-4 for EMBERWOOD");
                userInput = input.nextInt();
                input.nextLine();
                if (userInput >= 1 && userInput <= 4) {
                    returnRegion(userInput);
                    locationGetter();
                    System.out.println("Enter in your location: ");
                    mapLocation = input.nextLine();
                    coordinateGetter();
                    if (checker) {
                        System.out.println("your coords: ");
                        System.out.println(x + "," + y);
                        destination.setX(x);
                        destination.setY(y);
                        enteredLocation = true;
                    }
                } else continue;

            } catch (InputMismatchException e) {
                System.out.println("enter region from the given options: ");
                input.nextLine();
            }
        }
    }
    @Override
    public void returnRegion(int i) {
        if (i == 1) regions = mapRegions.EVERGREEN;
        else if (i == 2) regions = mapRegions.SUNHAVEN;
        else if (i == 3) regions = mapRegions.FROSTFIELD;
        else if (i == 4) regions = mapRegions.EMBERWOOD;
    }

    @Override
    public void locationGetter() {
        //mapRegions mapregions = mapRegions.valueOf(mapRegion);
        String line;
        try(BufferedReader mapLocation = new BufferedReader(new FileReader(filePath))) {
            mapLocation.readLine();
            while ((line = mapLocation.readLine()) != null) {
                String[] data = line.split(", ");
                mapRegions region = mapRegions.valueOf(data[0]);
                if (regions.equals(region)) {
                    regionLocation = data[1].trim();
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

    public void coordinateGetter() {
        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                regionLocation = data[1];
                if (mapLocation.equalsIgnoreCase(regionLocation)) {
                    x = Integer.parseInt(data[2]);
                    y = Integer.parseInt(data[3]);
                    checker = true;
                    break;
                }
                //System.out.println(region + regionLocation + x + y);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
