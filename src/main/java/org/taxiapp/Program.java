package org.taxiapp;

import org.taxiapp.Aesthetics.Icons;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Program {

    /** The app launcher */
    Customer customer = new CustomerLocation();
    VehicleHiring vehicleHiring = new VehicleHiring();
    Map map = new Map();

    public Program() throws IOException {
    }

    public void launch() throws NoSuchAlgorithmException, FileNotFoundException {
        customer.signIn();
        BankAccount.addFunds(customer);
        customer.getCustomerLocation();
        customer.getCustomerDestination();
        Taxi taxi = new TaxiRating();
        vehicleHiring.getATaxi(customer);
        map.moveToTarget(taxi, customer.location.getX(), customer.location.getY(), Icons.person);
        map.moveToTarget(taxi, customer.destination.getX(), customer.destination.getY(), Icons.destination);
        customer.tripExperience();
        taxi.setRating(vehicleHiring.chosenTaxi, customer);
        //taxi.getAverageRating(vehicleHiring.chosenTaxi);
        BankAccount.tipTaxi(customer);
    }

}