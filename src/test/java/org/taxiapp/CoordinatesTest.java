package org.taxiapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @Test
    void retrieveCoordinates1() {
        // Testing the happy path
        mapRegions region = mapRegions.EVERGREEN;
        String locationName = "Madge's Café";
      Coordinates testCoordinates = new Coordinates();
      int [] actualCoords = testCoordinates.retrieveCoordinates(region, locationName);
      assertEquals(2, actualCoords[0]);
      assertEquals(4, actualCoords[1]);


    }

}