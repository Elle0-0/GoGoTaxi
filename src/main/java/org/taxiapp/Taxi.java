package org.taxiapp;

import org.taxiapp.TaxiManagement.Vehicle;
import org.taxiapp.TaxiManagement.VehicleTypes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class Taxi extends User{

    // Attributes
    double Rate;
    int Rating;
    Vehicle Taxi;
    mapRegions region;
    String locationName;
    Location coordinates;

    /*when calling this from the launcher, you will need to check that no other taxis in the array of taxis
     has the same info */
    public Taxi(){
        // initialises the objects
        Taxi = new Vehicle();
        coordinates = new Location();
    }
    public void assignRandomInformation(){
        File file = new File("src/main/java/org/taxiapp/Files/taxiInformation.txt");
        int length = 0;

        try{
            // gets the number of lines in the file
            length = (int) Files.lines(file.toPath()).count();
        } catch (IOException e){
            System.out.println("Error counting lines");
            e.printStackTrace();
        }

        // generates a random number between 0 and the length of the file to choose a random line of information
        int random = (int) (Math.random() * length );

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String Line;
            reader.readLine();
            while ((Line = reader.readLine()) != null){
               int  i = 0;
               while (i < random){
                   reader.readLine();
                   i++;
               }
               // stores the values in taxiInformation.txt into accessible variables
               String[] fileData = Line.split(", ");
               name = fileData[0];
               Taxi.setVehicleType(VehicleTypes.valueOf(fileData[1]));
               Rate = Taxi.getVehicleType().rate;
               Rating =Integer.valueOf(fileData[2]);
               Taxi.setCarReg(fileData[3]);
               region = mapRegions.valueOf(fileData[4]);
               locationName = fileData[5];


            }

        } catch (IOException e){
            System.out.println("Error handling files");
            e.printStackTrace();
        }
        // stores the coordinates of the taxi's randomly generated location
        Coordinates taxiLocation = new Coordinates();
        int[] taxiCoords = taxiLocation.retrieveCoordinates(region, locationName);
        coordinates.setX(taxiCoords[0]); coordinates.setY(taxiCoords[1]);

    }
    public void displayInformation(){
        System.out.println("Name: " + name);
        System.out.println("Ride Type: " + Taxi.getVehicleType());
        System.out.println("Rate per km: €" + Rate);
        System.out.println("Rating: " + Rating);
        System.out.println("Car Registration: " + Taxi.getCarReg());
        System.out.println("Location: " + region + ", " + locationName);
    }



}
