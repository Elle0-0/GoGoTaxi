package org.taxiapp;

import org.junit.jupiter.api.Test;
import org.taxiapp.Aesthetics.Icons;
import org.taxiapp.TaxiManagement.VehicleTypes;

import java.io.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class CustomLinkedListTest {
     /**
     testing the two most important functions in the CustomLinkedListTest
     Class; Contains and SortInsert
     **/
    @Test
    public void testContains() throws IOException {
        VehicleHiring vh = new VehicleHiring();
        Customer customer = new Customer();
        vh.initialiseTaxis();
        vh.moveTaxis();
        // testing the happy path when the user chooses the centre most location
        // FROSTFIELD, Sycamore Lane, 4, 5
        customer.location.setX(4);
        customer.location.setY(5);
        // just incase there is only one taxi in range, it is safest to pick the first one
        int testInput = 1;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        InputStream inputStream = new ByteArrayInputStream(String.valueOf(testInput).getBytes());
        System.setIn(inputStream);
        VehicleTypes taxiType = VehicleTypes.PREMIUM;
        vh.getTaxisInRange(customer, taxiType );
        ArrayList<Taxi> taxis =vh.availableTaxisList.arrayOfTaxis();
        // proves that all the taxi names that have been recorded to avoid duplication
        for (Taxi t : taxis){
            assertEquals(true, vh.names.contains(t.getName()));
        }
    }

    @Test
    public void testSortInsert() throws IOException{
        VehicleHiring vh = new VehicleHiring();
        Customer customer = new Customer();
        vh.initialiseTaxis();
        vh.moveTaxis();
        // testing the happy path when the user chooses the centre most location
        // FROSTFIELD, Sycamore Lane, 4, 5
        customer.location.setX(4);
        customer.location.setY(5);
        int customerX = 4;
        int customerY = 5;


        // just incase there is only one taxi in range, it is safest to pick the first one
        int testInput = 1;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        InputStream inputStream = new ByteArrayInputStream(String.valueOf(testInput).getBytes());
        System.setIn(inputStream);
        VehicleTypes taxiType = VehicleTypes.PREMIUM;
        vh.getTaxisInRange(customer, taxiType );
        ArrayList<Taxi> taxis =vh.availableTaxisList.arrayOfTaxis();
       Taxi closestTaxi = (Taxi) vh.availableTaxisList.getChosenTaxi(1);
       int taxiX = closestTaxi.location.getX();
       int taxiY = closestTaxi.location.getY();
       double perpDistance = Math.sqrt(Math.pow((customerX - taxiX), 2) + Math.pow((customerY - taxiY), 2));
       double shortestDistance = perpDistance;
       for (Taxi t: taxis){
           taxiX = t.location.getX();
           taxiY = t.location.getY();
           double currentDistance = perpDistance;
           assertEquals(true, currentDistance >= shortestDistance);
       }



    }
}
