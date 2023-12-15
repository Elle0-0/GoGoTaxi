package org.taxiapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @Test
    void retrieveCoordinates1() {
        // Testing the happy path
        mapRegions region = mapRegions.EVERGREEN;
        String locationName = "Madges Cafe";
      Coordinates testCoordinates = new Coordinates();
      int [] actualCoords = testCoordinates.retrieveCoordinates(region, locationName);
      assertEquals(2, actualCoords[0]);
      assertEquals(4, actualCoords[1]);
    }
    @Test
    void retrieveCoordinates2(){
        // what if they were all lowercase
        mapRegions region =mapRegions.EVERGREEN;
        String locationName = "madges cafe";
        Coordinates testCoordinates = new Coordinates();
        int [] actualCoords = testCoordinates.retrieveCoordinates(region, locationName);
        assertEquals(2, actualCoords[0]);
        assertEquals(4, actualCoords[1]);
    }
    @Test
    void retrieveCoordinates3(){
        // what if they were all Uppercase
        mapRegions region =mapRegions.EVERGREEN;
        String locationName = "MADGES CAFE";
        Coordinates testCoordinates = new Coordinates();
        int [] actualCoords = testCoordinates.retrieveCoordinates(region, locationName);
        assertEquals(2, actualCoords[0]);
        assertEquals(4, actualCoords[1]);
    }
}