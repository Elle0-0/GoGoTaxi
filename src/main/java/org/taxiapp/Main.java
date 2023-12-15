package org.taxiapp;

import org.taxiapp.Aesthetics.Icons;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
       // Program myProgram = new Program();
       // myProgram.launch();
        VehicleHiring hiring = new VehicleHiring();
        Customer customer = new CustomerLocation();
        //customer.signIn();
        customer.getCustomerLocation();
        customer.getCustomerDestination();
        Taxi taxi = new taxiRating();
        hiring.getATaxi(customer);
        Map map = new Map();
        map.moveToTarget(taxi, customer.location.getX(), customer.location.getY(), Icons.person);
        map.moveToTarget(taxi, customer.destination.getX(), customer.destination.getY(), Icons.destination);
        customer.tripExperience();
        taxi.setRating(taxi, customer);
        System.out.println(customer.getRating());
    }
}