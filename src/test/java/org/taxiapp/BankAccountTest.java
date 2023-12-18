package org.taxiapp;

import org.junit.jupiter.api.Test;
import org.taxiapp.TaxiManagement.VehicleTypes;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTest {
    @Test
    public void getCostOfTripTest() {
        Taxi taxi = new Taxi();
        double distanceTravelled = 30;

        taxi.setKmTravelled(distanceTravelled);
        taxi.getTaxi().setVehicleType(VehicleTypes.REGULAR);
        assertEquals(BankAccount.getCostOfTrip(taxi), distanceTravelled*VehicleTypes.REGULAR.rate);

        taxi.setKmTravelled(distanceTravelled);
        taxi.getTaxi().setVehicleType(VehicleTypes.PREMIUM);
        assertEquals(BankAccount.getCostOfTrip(taxi), distanceTravelled* VehicleTypes.PREMIUM.rate);
    }

    @Test
    public void checkFundsTest() {
        Customer customer = new Customer();
        customer.setUsername("a");
        double fundLimit = 20;
        double customerFunds =BankAccount.calculateFunds(customer);
        boolean expected = !(fundLimit > customerFunds);
        boolean actual = BankAccount.checkFunds(customer);
        assertEquals(actual,expected);
    }
}
