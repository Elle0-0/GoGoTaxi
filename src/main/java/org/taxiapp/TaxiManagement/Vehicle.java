package org.taxiapp.TaxiManagement;

public class Vehicle {
    private VehicleTypes vehicleType; // whether it is premium or regular
    private String carReg; // car identifier

    public VehicleTypes getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleTypes vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getCarReg() {
        return carReg;
    }

    public void setCarReg(String carReg) {
        this.carReg = carReg;
    }
}
