package org.taxiapp;

import org.taxiapp.TaxiManagement.Vehicle;
import org.taxiapp.TaxiManagement.VehicleTypes;

import java.io.*;
import java.nio.file.Files;

import static org.taxiapp.Coordinates.retrieveCoordinates;

public class Taxi extends User{

    // Attributes
   private double Rate;
    private double Rating;
    private Vehicle Taxi;
    private mapRegions region;
    private String locationName;
    private double kmTravelled;

    /*when calling this from the launcher, you will need to check that no other taxis in the array of taxis
     has the same info */
    public Taxi(){
        // initialises the objects
        Taxi = new Vehicle();
        location = new Location();
    }

    public double getKmTravelled() {
        return kmTravelled;
    }

    public void setKmTravelled(double kmTravelled) {
        this.kmTravelled = kmTravelled;
    }

    public Vehicle getTaxi() {
        return Taxi;
    }

    public void setRegion(mapRegions region) {
        this.region = region;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public mapRegions getRegion() {
        return region;
    }
    public String getLocationName() {
        return locationName;
    }
    public String getName() {
        return name;
    }
    public void setRating(Taxi taxi, Customer customer) {}
    public double getAverageRating(Taxi taxi) {return Rating;}
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
        int random = (int) (Math.random() * (length -1) );

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String Line = "";
            int i = 0;
            reader.readLine();
            while (((Line = reader.readLine()) != null)){
              if (i == random){
                  // stores the values in taxiInformation.txt into accessible variables

                  String[] fileData = Line.split(", ");
                  name = fileData[0];
                  Taxi.setVehicleType(VehicleTypes.valueOf(fileData[1]));
                  Rate = Taxi.getVehicleType().rate;
                  //Rating = getAverageRating();
                  Taxi.setCarReg(fileData[2]);
                  setRegion(mapRegions.valueOf(fileData[3]));
                  setLocationName(fileData[4]);
              }
              i ++;
            }
        } catch (IOException e){
            System.out.println("Error handling files");
            e.printStackTrace();
        }
        // stores the coordinates of the taxi's randomly generated location
        Coordinates taxiLocation = new Coordinates();
        int[] taxiCoords = retrieveCoordinates(region, locationName);
        location.setX(taxiCoords[0]); location.setY(taxiCoords[1]);

    }
    public String displayInformation(){
        System.out.println("Name: " + name);
        System.out.println("Ride Type: " + Taxi.getVehicleType());
        System.out.println("Rate per km: €" + Rate);
        //System.out.print("Rating: " + getAverageRating());
        System.out.println();
        System.out.println("Car Registration: " + Taxi.getCarReg());
        System.out.println("Location: " + getRegion() + ", " + getLocationName());
        return "";
    }
    public void loopedMovement(){
        // find the current coordinates in the maplocation txt
        // move to the next line and store those coordinates
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/org/taxiapp/Files/mapLocations.txt"));
            String Line = "";
            reader.readLine(); // skips the first line of information

            while ((Line = reader.readLine())!= null){
                String[] fileData = Line.split(", ");

                if (fileData[1].equals(locationName)) {
                    break;
                }
            }
            // go to the next line after the orginal location has been found
            Line = reader.readLine();
            // if there is a next line available, set the location to that
            if (Line !=null){
                String[] newData = Line.split(", ");
                setRegion(mapRegions.valueOf(newData[0]));
                setLocationName(newData[1]);
            }else {
                // if at the last line of the location, do a wrap around
                setRegion(mapRegions.FROSTFIELD);
                setLocationName("Oakridge Estates");

            }


        }catch (IOException e){
            System.out.println("Error handling files");
            e.printStackTrace();
        }
        int[] newCoords = retrieveCoordinates(getRegion(), getLocationName());
        location.setX(newCoords[0]);
        location.setY(newCoords[1]);
    }


}
