package org.taxiapp;

import org.taxiapp.Aesthetics.Colors;
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
        Taxi chosenTaxi = vehicleHiring.getATaxi(customer);
        map.moveToTarget(chosenTaxi, customer.location.getX(), customer.location.getY(), Icons.person, Colors.blue);
        map.moveToTarget(chosenTaxi, customer.destination.getX(), customer.destination.getY(), Icons.destination, Colors.pink);
        System.out.println(chosenTaxi.getKmTravelled());
        customer.tripExperience();
        taxi.setRating(vehicleHiring.chosenTaxi, customer);
        taxi.getAverageRating(vehicleHiring.chosenTaxi);
        BankAccount.tipTaxi(customer);
    }

}