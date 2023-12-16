package org.taxiapp;
import org.taxiapp.Aesthetics.Colors;
import org.taxiapp.Aesthetics.Icons;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Program {
    Customer customer = new CustomerLocation();
    VehicleHiring vehicleHiring = new VehicleHiring();
    Map map = new Map();

    public Program() throws IOException {
    }

    public void launch() throws NoSuchAlgorithmException, FileNotFoundException {

        customer.signIn();
        customer.getCustomerLocation();
        customer.getCustomerDestination();
        System.out.println(customer.destination.getX());
        System.out.println(customer.destination.getY());
        Taxi taxi = vehicleHiring.getATaxi(customer);
        map.moveToTarget(taxi, customer.location.getX(), customer.location.getY(), Icons.person, Colors.blue);
        map.moveToTarget(taxi, customer.destination.getX(), customer.destination.getY(), Icons.destination, Colors.pink);
        customer.tripExperience();


    }

}