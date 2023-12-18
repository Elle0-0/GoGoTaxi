package org.taxiapp;

import java.io.IOException;

public interface vehicleHiringTestInterface{
    public abstract void testAddToMap() throws IOException;
    public abstract void testMoveVehicle() throws IOException;
    public abstract void testRemoveVehicle() throws IOException;
    public abstract void testGetVehicle() throws IOException;
    public abstract void testGetVehiclesInRange() throws IOException;
}