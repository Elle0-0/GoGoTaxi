package org.taxiapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Coordinates {


    public static int[] retrieveCoordinates(mapRegions region, String locationName){
        int[] XYCoords = new int[2];

        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/org/taxiapp/Files/mapLocations.txt"));
            String Line;
            reader.readLine();
            while ((Line = reader.readLine()) != null){
                String[] fileData = Line.split(", ");
                if (mapRegions.valueOf(fileData[0]).equals(region) && fileData[1].equalsIgnoreCase(locationName)){
                    XYCoords[0] = Integer.parseInt(fileData[2]);
                    XYCoords[1] = Integer.parseInt(fileData[3]);
                    break;
                }
            }

        }catch (IOException e){
            System.out.println("Error handling files");
            e.printStackTrace();

        }
        return XYCoords;
    }
}
