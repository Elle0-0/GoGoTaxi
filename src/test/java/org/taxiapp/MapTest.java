package org.taxiapp;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {


    @Test
    void changeCoord1() {
        // both coordinates are in bounds
        Map testMap = new Map();
        int inBoundX = 5;
        int inBoundY = 6;
        // a true output means that there was no error in the first place
        assertEquals(true, testMap.changeCoord(inBoundX, inBoundY, ":)") );

    }
    @Test
    void changeCoord2(){
        // x is out of bounds
        Map testMap = new Map();
        int outBoundX = 11;
        int inBoundY = 9;
        // a false output means that there was an error,but it was handled
        assertEquals(false, testMap.changeCoord(outBoundX, inBoundY, ":)") );
    }
    @Test
    void changeCoord3(){
        // Y is out of bounds
        Map testMap = new Map();
        int inBoundX = 5;
        int outBoundY = -5;
        // a false output means that there was an error,but it was handled
        assertEquals(false, testMap.changeCoord(inBoundX, outBoundY, ":)") );
    }
@Test
void changeC0ord4(){
        // both are out of bounds
    Map testMap = new Map();
    int outBoundX = 37;
    int outBoundY = -67;
    // a false output means that there was an error,but it was handled
    assertEquals(false, testMap.changeCoord(outBoundX, outBoundY, ":)") );

}

}