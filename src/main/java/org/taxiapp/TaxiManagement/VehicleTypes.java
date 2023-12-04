package org.taxiapp.TaxiManagement;

public enum VehicleTypes {
    // stores the type of taxi, and it's rate per kilometer
    REGULAR(6.90),
    PREMIUM(4.20);

    public final double rate;

     VehicleTypes(double rate) {
        this.rate = rate;
    }
}
