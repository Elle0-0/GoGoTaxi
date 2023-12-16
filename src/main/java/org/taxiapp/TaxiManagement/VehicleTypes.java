package org.taxiapp.TaxiManagement;

public enum VehicleTypes {
    // stores the type of taxi, and it's rate per kilometer
    REGULAR(1.30),
    PREMIUM(2.00);

    public final double rate;

     VehicleTypes(double rate) {
        this.rate = rate;
    }
}
