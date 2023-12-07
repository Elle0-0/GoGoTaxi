package org.taxiapp.TaxiManagement;

public enum VehicleTypes {
    // stores the type of taxi, and it's rate per kilometer
    REGULAR(4.20),
    PREMIUM(6.90);

    public final double rate;

     VehicleTypes(double rate) {
        this.rate = rate;
    }
}
