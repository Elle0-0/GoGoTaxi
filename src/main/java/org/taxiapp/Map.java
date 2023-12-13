package org.taxiapp;

import org.taxiapp.Aesthetics.Icons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;

public class Map {
    private String[][] Map = new String[10][10];
    int[][] blockedCoordinates;

    public void establishMap() {
        for (String[] row : Map) {
            for (int i = 0; i < row.length; i++) {
                row[i] = " . ";

            }
        }

    }

    public void printMap() {
        for (String[] row : Map) {
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i]);
            }
            System.out.println();
        }
    }

    public boolean changeCoord(int x, int y, String icon) {
        try {
            Map[x][y] = icon;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Hmm, it seems you are off the map.");
            System.out.println("Don't worry, we'll handle it!");
            return false;
        }
        return true;

    }

    public void moveToTarget(Taxi selectedTaxi, int targetX, int targetY, String icon) {
        establishMap();
        boolean outOfBounds = false;
        int taxiX = selectedTaxi.location.getX();
        int taxiY = selectedTaxi.location.getY();
        //changeCoord(taxiX, taxiY, Icons.chosenTaxi);
        changeCoord(targetX, targetY, icon);
        while ((taxiX != targetX) && (taxiY != targetY)) {
            if (taxiY < targetY) {
                while (taxiY != targetY) {
                    taxiY++;

                    changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                    changeCoord(taxiX, (taxiY - 1), " + ");
                    printMap();
                    System.out.println();
                }
            } else if (taxiY > targetY) {
                while (taxiY != targetY) {
                    taxiY--;
                    changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                    changeCoord(taxiX, (taxiY + 1), " + ");
                    printMap();
                    System.out.println();
                }

            }
            if (taxiX < targetX) {
                while (taxiX != targetX) {
                    taxiX++;
                    // ensure it can't go out of bounds
                    changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                    changeCoord((taxiX - 1), taxiY, " + ");
                    printMap();
                    System.out.println();
                }
            } else if (taxiX > targetX) {
                while (taxiX != targetX) {
                    taxiX--;
                    changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                    changeCoord((taxiX + 1), taxiY, " + ");
                    printMap();
                    System.out.println();
                }
            }
        }

        selectedTaxi.location.setX(taxiX);
        selectedTaxi.location.setY(taxiY);
        System.out.println("The taxi has arrived!");
    }
}


