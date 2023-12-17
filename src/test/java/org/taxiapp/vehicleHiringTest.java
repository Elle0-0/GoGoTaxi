package org.taxiapp;

import org.junit.jupiter.api.Test;
import org.taxiapp.Aesthetics.Colors;
import org.taxiapp.Aesthetics.Icons;
import org.taxiapp.TaxiManagement.VehicleTypes;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class vehicleHiringTest implements vehicleHiringTestInterface {

    @Test
    @Override
    public void testGetVehiclesInRange() throws IOException {
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
        assertNotEquals(0, vh.getAvailableTaxisList().size);

    }

    @Test
    public void testGetVehiclesInRange2() throws IOException{
        VehicleHiring vh = new VehicleHiring();
        Customer customer = new Customer();
        vh.initialiseTaxis();
        vh.moveTaxis();
        // testing the most isolated location on the map
        // FROSTFIELD, Oakridge Estates, 0, 6
        customer.location.setX(0);
        customer.location.setY(6);
        // just incase there is only one taxi in range, it is safest to pick the first one
        int testInput = 1;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        InputStream inputStream = new ByteArrayInputStream(String.valueOf(testInput).getBytes());
        System.setIn(inputStream);

        VehicleTypes taxiType = VehicleTypes.PREMIUM;
        vh.getTaxisInRange(customer, taxiType);
        assertNotEquals(0, vh.getAvailableTaxisList().size);

    }

    @Test
    @Override
    public void testAddToMap() throws IOException {
        // adding the taxis in range to the map
        VehicleHiring vh = new VehicleHiring();
        vh.initialiseTaxis();

        Customer customer = new Customer();
        customer.location.setX(4);
        customer.location.setY(5);

        int testInput = 1;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        InputStream inputStream = new ByteArrayInputStream(String.valueOf(testInput).getBytes());
        System.setIn(inputStream);
        VehicleTypes taxiType = VehicleTypes.PREMIUM;
        vh.getTaxisInRange(customer, taxiType);
        ArrayList<Taxi> taxisInRange = vh.getAvailableTaxisList().arrayOfTaxis();
        for (Taxi taxi : taxisInRange){
           assertEquals( Icons.allcars, vh.getWorldMap().getIcon(taxi.location.getX(), taxi.location.getY()));
        }


    }
    @Test
     public void testAddToMap2() throws IOException{
        // adding the chosenTaxi to the map
        VehicleHiring vh = new VehicleHiring();
        Map map = new MoveToTarget();
        vh.initialiseTaxis();

        Customer customer = new Customer();
        customer.location.setX(4);
        customer.location.setY(5);

        int testInput = 1;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        InputStream inputStream = new ByteArrayInputStream(String.valueOf(testInput).getBytes());


        vh.initialiseTaxis();
        VehicleTypes taxiType = VehicleTypes.PREMIUM;
        System.out.println("Please choose a taxi");
        System.out.println("The Taxi's nearest to you are: ");
        System.setIn(inputStream);
        Taxi chosenTaxi = vh.getTaxisInRange(customer, taxiType);

       // Taxi chosenTaxi = vh.getATaxi(customer);
        int initialX = chosenTaxi.location.getX();
        int initialY = chosenTaxi.location.getY();
        map.moveToTarget(chosenTaxi,customer.location.getX(), customer.location.getY(), Icons.person, Colors.blue);
        /* as there is no point in the program where there is a constant display of the
        location of the chosen taxi, however, when the taxi is moved it leaves a trail.
         this test proves that the chosen taxi was displayed on the map at one point*/
        String path = (Colors.blue + " + " + Colors.reset);

        assertEquals(path, map.getIcon(initialX, initialY));

    }

    @Test
    @Override
    public void testRemoveVehicle() throws IOException{
        /* test to show that once a taxi has been selected, all unselected
        taxis have been removed from the map */
        VehicleHiring vh = new VehicleHiring();
        Map map = new MoveToTarget();
        vh.initialiseTaxis();

        Customer customer = new Customer();
        customer.location.setX(4);
        customer.location.setY(5);

        int testInput = 1;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        InputStream inputStream = new ByteArrayInputStream(String.valueOf(testInput).getBytes());

        vh.initialiseTaxis();
        VehicleTypes taxiType = VehicleTypes.PREMIUM;
        System.out.println("Please choose a taxi");
        System.out.println("The Taxi's nearest to you are: ");
        System.setIn(inputStream);
        Taxi chosenTaxi = vh.getTaxisInRange(customer, taxiType);


        int initialX = chosenTaxi.location.getX();
        int initialY = chosenTaxi.location.getY();
        map.moveToTarget(chosenTaxi,customer.location.getX(), customer.location.getY(), Icons.person, Colors.blue);
        ArrayList<Taxi> taxisInRange = vh.getAvailableTaxisList().arrayOfTaxis();
        for (Taxi taxi : taxisInRange){
            assertNotEquals( Icons.allcars, map.getIcon(taxi.location.getX(), taxi.location.getY()));
        }



    }

    @Test
    @Override
    public void testMoveVehicle() throws IOException{
        // moving the vehicle to the customer
        VehicleHiring vh = new VehicleHiring();
        Map map = new MoveToTarget();
        vh.initialiseTaxis();

        Customer customer = new Customer();
        customer.location.setX(4);
        customer.location.setY(5);

        int testInput = 1;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        InputStream inputStream = new ByteArrayInputStream(String.valueOf(testInput).getBytes());
        System.setIn(inputStream);

        vh.initialiseTaxis();
        VehicleTypes taxiType = VehicleTypes.PREMIUM;
        System.out.println("Please choose a taxi");
        System.out.println("The Taxi's nearest to you are: ");
        System.setIn(inputStream);
        Taxi chosenTaxi = vh.getTaxisInRange(customer, taxiType);

        map.moveToTarget(chosenTaxi, customer.location.getX(), customer.location.getY(), Icons.person, Colors.blue);
        assertEquals(customer.location.getX(), chosenTaxi.location.getX());
        assertEquals(customer.location.getY(), chosenTaxi.location.getY());
    }

    @Test
    public void testMoveVehicle2() throws IOException{
        // moving to the destination
        VehicleHiring vh = new VehicleHiring();
        Map map = new MoveToTarget();
        vh.initialiseTaxis();

        Customer customer = new Customer();
        customer.location.setX(4);
        customer.location.setY(5);

        int testInput = 1;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        InputStream inputStream = new ByteArrayInputStream(String.valueOf(testInput).getBytes());
        System.setIn(inputStream);

        vh.initialiseTaxis();
        VehicleTypes taxiType = VehicleTypes.PREMIUM;
        System.out.println("Please choose a taxi");
        System.out.println("The Taxi's nearest to you are: ");
        System.setIn(inputStream);
        Taxi chosenTaxi = vh.getTaxisInRange(customer, taxiType);

        map.moveToTarget(chosenTaxi, customer.location.getX(), customer.location.getY(), Icons.person, Colors.blue);
        map.moveToTarget(chosenTaxi, customer.destination.getX(), customer.destination.getY(), Icons.destination, Colors.pink);
        assertEquals(customer.destination.getX(), chosenTaxi.location.getX());
        assertEquals(customer.destination.getY(), chosenTaxi.location.getY());
    }

    @Test
    @Override
    public void testGetVehicle() throws IOException {
        VehicleHiring vh = new VehicleHiring();
        Customer customer = new Customer();
        vh.initialiseTaxis();
        vh.moveTaxis();
        // testing the most isolated location on the map
        // FROSTFIELD, Oakridge Estates, 0, 6
        customer.location.setX(0);
        customer.location.setY(6);
        // just incase there is only one taxi in range, it is safest to pick the first one
        int testInput = 1;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        InputStream inputStream = new ByteArrayInputStream(String.valueOf(testInput).getBytes());
        vh.initialiseTaxis();
        VehicleTypes taxiType = VehicleTypes.PREMIUM;
        System.out.println("Please choose a taxi");
        System.out.println("The Taxi's nearest to you are: ");
        System.setIn(inputStream);
        Taxi actualChosenTaxi = vh.getTaxisInRange(customer, taxiType);
        Taxi expectedActualTaxi = (Taxi) vh.getAvailableTaxisList().getChosenTaxi(testInput-1);
        assertEquals(expectedActualTaxi, actualChosenTaxi);

    }

    @Test
    public void testLoopedMovement(){
        Taxi taxi = new Taxi();
        // ensuring that the loopeed movement loops around
        // edge case, testing that after the looped movement function has been ran
        // if the taxi is at the last line of the file, it wont error but instead will loop around to the first

        /** EMBERWOOD, Esker Hills, 9, 7  (this is the last line of the file) **/
        /**FROSTFIELD, Oakridge Estates, 0, 6 (the location the taxi should arrive at) **/
        taxi.location.setX(9);
        taxi.location.setY(7);
        taxi.loopedMovement();
        assertEquals(mapRegions.FROSTFIELD, taxi.getRegion());
        assertEquals("Oakridge Estates", taxi.getLocationName());
        assertEquals(0, taxi.location.getX());
        assertEquals(6, taxi.location.getY());

    }

    @Test
    public void testPreferredTaxiType() throws IOException {
        /** for the previous gettaxiInRange tests, i used the premium taxi type
         *  for this test i will use regular and test to see that only regular taxis appear
         */
        VehicleHiring vh = new VehicleHiring();
        Customer customer = new Customer();
        vh.initialiseTaxis();
        vh.moveTaxis();
        // testing the most isolated location on the map
        // FROSTFIELD, Oakridge Estates, 0, 6
        customer.location.setX(0);
        customer.location.setY(6);
        // just incase there is only one taxi in range, it is safest to pick the first one
        int testInput = 1;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        InputStream inputStream = new ByteArrayInputStream(String.valueOf(testInput).getBytes());
        System.setIn(inputStream);

        VehicleTypes taxiType = VehicleTypes.REGULAR;
        vh.getTaxisInRange(customer, taxiType);
        ArrayList<Taxi> taxis = vh.getAvailableTaxisList().arrayOfTaxis();
        for (Taxi t : taxis){
            assertEquals(VehicleTypes.REGULAR, t.getTaxi().getVehicleType());
        }

    }

}
