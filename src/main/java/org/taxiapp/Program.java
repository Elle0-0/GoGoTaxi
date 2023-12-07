package org.taxiapp;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

public class Program {
    Customer customer = new CustomerLocation();
    VehicleHiring vehicleHiring = new VehicleHiring();

    public void launch() throws NoSuchAlgorithmException, FileNotFoundException {
        customer.signIn();
        customer.insertDestination();
        vehicleHiring.initialiseTaxis();
        vehicleHiring.getTaxisInRange(customer);
        customer.calculateTimeTaken(vehicleHiring);
        customer.tripExperience();
        customer.getRating();
    }

}
