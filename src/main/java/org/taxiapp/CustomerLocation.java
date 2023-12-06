package org.taxiapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerLocation extends Customer {
    Scanner input = new Scanner(System.in);
    String filePath = "src/main/java/org/taxiapp/Files/mapLocations.txt";

    @Override
    public void insertDestination() {
        boolean enteredLocation = false;
        int userInput;
        while (!enteredLocation) {
            try {
                System.out.println("Enter region: \n-1 for EVERGREEN \n-2 for SUNHAVEN\n-3 for FROSTFIELD\n-4 for EMBERWOOD");
                userInput = input.nextInt();
                if (userInput == 1 || userInput == 2 || userInput == 3 || userInput == 4) {
                    returnRegion(userInput);
                } else continue;
                locationGetter();
                System.out.println("Enter in your location: ");
                input.nextLine();
                mapLocation = input.nextLine();
                if (mapLocation.equalsIgnoreCase(regionLocation)) {
                    System.out.println("your coords: ");
                    System.out.println(x + "," + y);
                    destination.setX(x);
                    destination.setY(y);
                }
                else continue;
                enteredLocation = true;
            } catch (InputMismatchException e) {
                System.out.println("enter region from the given options: ");
                input.nextLine();
            }
        }
    }
    @Override
    public void returnRegion(int i) {
        if (i == 1) mapRegion = "EVERGREEN";
        else if (i == 2) mapRegion = "SUNHAVEN";
        else if (i == 3) mapRegion = "FROSTFIELD";
        else if (i == 4) mapRegion = "EMBERWOOD";
    }

    @Override
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
}
