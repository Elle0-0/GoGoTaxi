package org.taxiapp;

import org.taxiapp.TaxiManagement.Vehicle;
import org.taxiapp.TaxiManagement.VehicleTypes;

import java.io.*;
import java.nio.file.Files;

public class Taxi extends User{

    // Attributes
   private double Rate;
    private int Rating;
    private Vehicle Taxi;
    private mapRegions region;
    private String locationName;
    private String icon;

    /*when calling this from the launcher, you will need to check that no other taxis in the array of taxis
     has the same info */
    public Taxi(){
        // initialises the objects
        Taxi = new Vehicle();
        location = new Location();
        icon = " \uD83D\uDE97";
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

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                  Rating = Integer.parseInt(fileData[2]);
                  Taxi.setCarReg(fileData[3]);
                  setRegion(mapRegions.valueOf(fileData[4]));
                  setLocationName(fileData[5]);
              }
              i ++;


            }



        } catch (IOException e){
            System.out.println("Error handling files");
            e.printStackTrace();
        }
        // stores the coordinates of the taxi's randomly generated location
        Coordinates taxiLocation = new Coordinates();
        int[] taxiCoords = taxiLocation.retrieveCoordinates(region, locationName);
        location.setX(taxiCoords[0]); location.setY(taxiCoords[1]);

    }
    public void displayInformation(){
        System.out.println("Name: " + name);
        System.out.println("Ride Type: " + Taxi.getVehicleType());
        System.out.println("Rate per km: €" + Rate);
        System.out.print("Rating: ");
        for (int i = 0; i < Rating; i++){
            System.out.print("★");
        }
        System.out.println();
        System.out.println("Car Registration: " + Taxi.getCarReg());
        System.out.println("Location: " + getRegion() + ", " + getLocationName());
    }
    public void randomMovement(){
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
    }

    public void getAllTaxis() {
        String line;
        String name, rideType, carreg, region, location;
        String filePath = "src/main/java/org/taxiapp/Files/taxiInformation.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            while((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                name = data[0];
                rideType = data[1];
                carreg = data[2];
                region = data[3];
                location = data[4];
                System.out.println(name + " " + rideType + " " + carreg + " " + region + " " + location);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Taxi t = new Taxi();
        t.getAllTaxis();
    }
}
