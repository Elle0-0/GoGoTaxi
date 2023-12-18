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

    Taxi taxi = new TaxiRating();
    Map map = new MoveToTarget();


    public Program() throws IOException {
    }

    public void launch() throws NoSuchAlgorithmException, FileNotFoundException {
        customer.signIn();
        BankAccount.addFunds(customer);
        if (BankAccount.checkFunds(customer)) {
            customer.getCustomerLocation();
            customer.getCustomerDestination();
            vehicleHiring.getATaxi(customer);
            map.moveToTarget(vehicleHiring.chosenTaxi, customer.location.getX(), customer.location.getY(), Icons.person, Colors.blue);
            map.moveToTarget(vehicleHiring.chosenTaxi, customer.destination.getX(), customer.destination.getY(), Icons.destination, Colors.pink);
            customer.tripCost(customer, vehicleHiring.chosenTaxi);
            customer.tripExperience();
            taxi.setRating(vehicleHiring.chosenTaxi, customer);
            BankAccount.tipTaxi(customer);
        }
        else {
            System.out.println("Not enough money in your account, please add funds to continue.\n Ending application");
        }
    }

}