package org.taxiapp;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    public void insertDestinationTest() throws IOException {

    }

    @Test
    public void returnRegion() throws IOException {
        Customer c = new CustomerLocation();
        assertEquals(c.returnRegion(1), mapRegions.EVERGREEN);
        assertEquals(c.returnRegion(2), mapRegions.SUNHAVEN);
        assertEquals(c.returnRegion(3), mapRegions.FROSTFIELD);
        assertEquals(c.returnRegion(4), mapRegions.EMBERWOOD);
        assertNull(c.returnRegion(5));
    }
}
