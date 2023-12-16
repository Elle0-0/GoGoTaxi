package org.taxiapp;

import org.taxiapp.TaxiManagement.Vehicle;
import org.taxiapp.TaxiManagement.VehicleTypes;

import java.io.*;
import java.nio.file.Files;

import static org.taxiapp.Coordinates.retrieveCoordinates;

public class Taxi extends User{

    // Attributes
   private double Rate;
    private int Rating;
    private final Vehicle Taxi;
    private mapRegions region;
    private String locationName;
    private double kmTravelled;

    /*when calling this from the launcher, you will need to check that no other taxis in the array of taxis
     has the same info */
    public Taxi(){
        // initialises the objects
        Taxi = new Vehicle();
        location = new Location();
        kmTravelled = 0;
    }

    public Vehicle getTaxi() {
        return Taxi;
    }

    public void setRate(double rate) {
        Rate = rate;
    }

    public void setRegion(mapRegions region) {
        this.region = region;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setKmTravelled(double kmTravelled) {
        this.kmTravelled = kmTravelled;
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

    public double getKmTravelled() {
        return kmTravelled;
    }

    public void setName(String name) {
        this.name = name;
    }


    // is used, just overrided
    public void setRating(Taxi taxi, Customer customer) {}


    public void assignRandomInformation(){
        File file = new File("src/main/java/org/taxiapp/Files/taxiInformation.txt");
        int length = 0;

        try{
            // gets the number of lines in the file (not hardcoded length incase length changes)
            length = (int) Files.lines(file.toPath()).count();
        } catch (IOException e){
            System.out.println("Error counting lines");
            e.getLocalizedMessage();
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
                  setName(fileData[0]);
                  Taxi.setVehicleType(VehicleTypes.valueOf(fileData[1]));
                  setRate(Taxi.getVehicleType().rate);
                  //Rating = Integer.parseInt(fileData[2]);
                  Taxi.setCarReg(fileData[2]);
                  setRegion(mapRegions.valueOf(fileData[3]));
                  setLocationName(fileData[4]);
              }
              i ++;
            }
        } catch (IOException e){
            System.out.println("Error handling files");
            e.getLocalizedMessage();
        }
        // stores the coordinates of the taxi's randomly generated location
        int[] taxiCoords = retrieveCoordinates(region, locationName);
        location.setX(taxiCoords[0]); location.setY(taxiCoords[1]);

    }
    public String displayInformation(){
        System.out.println("Name: " + name);
        System.out.println("Ride Type: " + Taxi.getVehicleType());
        System.out.println("Rate per km: €" + Rate);
        /**System.out.print("Rating: ");
        for (int i = 0; i < Rating; i++){
            System.out.print("★");
        } **/
        //System.out.println();
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
            e.getLocalizedMessage();
        }
        int[] newCoords = retrieveCoordinates(getRegion(), getLocationName());
        location.setX(newCoords[0]);
        location.setY(newCoords[1]);
    }

}
