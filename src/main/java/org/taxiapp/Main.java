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
        Map map = new Map();
        customer.signIn();
        customer.getCustomerLocation();
        customer.getCustomerDestination();
        //System.out.println(customer.destination.getX());
        //System.out.println(customer.destination.getY());
        Taxi taxi = hiring.getATaxi(customer);
        map.moveToTarget(taxi, customer.location.getX(), customer.location.getY(), Icons.person);
        map.moveToTarget(taxi, customer.destination.getX(), customer.destination.getY(), Icons.destination);
        // customer.tripExperience();


    }
}