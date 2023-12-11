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

        try {
            blockedCoordinates = getBlockedCoordinates();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < blockedCoordinates.length; i ++){
            changeCoord(blockedCoordinates[i][0], blockedCoordinates[i][1], Icons.restricted);
        }


    }
    public void printMap(){
        for (String[] row : Map){
            for (int i = 0; i < row.length ; i ++){
                System.out.print(row[i]);
            }
            System.out.println();
        }
    }
    public boolean changeCoord(int x, int y, String icon){
        try {
            Map[x][y] = icon;
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Hmm, it seems you are off the map.");
            System.out.println("Don't worry, we'll handle it!");
            return false;
        }
return true;

    }
    public void moveToTarget(Taxi selectedTaxi, int targetX, int targetY,String icon) {
        establishMap();
        boolean outOfBounds = false;
        int taxiX = selectedTaxi.location.getX();
        int taxiY = selectedTaxi.location.getY();
        changeCoord(targetX, targetY, icon);

        while ((taxiX != targetX || taxiY != targetY) && (taxiX >= 0 && taxiX < 10) && (taxiY >= 0 && taxiY < 10)) {
            if (taxiX < targetX) {
                while (taxiX != targetX && taxiX < 10) {
                    if (!isBlocked(taxiX + 1, taxiY)) {
                        taxiX++;
                        changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                        changeCoord((taxiX - 1), taxiY, " + ");
                        printMap();
                        System.out.println();
                    } else if (taxiY < targetY && (!isBlocked(taxiX, taxiY + 1))) {
                        taxiY++;
                        changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                        changeCoord(taxiX, taxiY - 1, " + ");
                        printMap();
                        System.out.println();
                    } else {
                        taxiY--;
                        changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                        changeCoord(taxiX, taxiY + 1, " + ");
                        printMap();
                        System.out.println();
                    }
                }
            } else if (taxiX > targetX) {
                while (taxiX != targetX && taxiX >= 0) {
                    if (!isBlocked(taxiX--, taxiY)) {
                        taxiX--;
                        changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                        changeCoord((taxiX ++), taxiY, " + ");
                        printMap();
                        System.out.println();
                    } else if (taxiY < targetY && (!isBlocked(taxiX, taxiY ++))) {
                        taxiY++;
                        changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                        changeCoord(taxiX, taxiY --, " + ");
                        printMap();
                        System.out.println();
                    } else {
                        taxiY--;
                        changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                        changeCoord(taxiX, taxiY ++, " + ");
                        printMap();
                        System.out.println();
                    }
                }
            }
            if (taxiY < targetY) {
                while (taxiY != targetY && taxiY < 10) {
                    if (!isBlocked(taxiX, taxiY ++)) {
                        taxiY++;
                        changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                        changeCoord(taxiX, (taxiY - 1), " + ");
                        printMap();
                        System.out.println();
                    } else if (taxiX < targetX && (!isBlocked(taxiX ++, taxiY))) {
                        taxiX++;
                        changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                        changeCoord(taxiX --, taxiY, " + ");
                        printMap();
                        System.out.println();
                    } else {
                        taxiX--;
                        changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                        changeCoord(taxiX ++, taxiY, " + ");
                        printMap();
                        System.out.println();
                    }
                }
            } else if (taxiY > targetY) {
                while (taxiY != targetY && taxiY >= 0) {
                    if (!isBlocked(taxiX, taxiY --)) {
                        taxiY--;
                        changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                        changeCoord(taxiX, (taxiY ++), " + ");
                        printMap();
                        System.out.println();
                    } else if (taxiX < targetX && (!isBlocked(taxiX ++, taxiY))) {
                        taxiX++;
                        changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                        changeCoord(taxiX --, taxiY, " + ");
                        printMap();
                        System.out.println();
                    } else {
                        taxiX--;
                        changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                        changeCoord(taxiX ++, taxiY, " + ");
                        printMap();
                        System.out.println();
                    }
                }
            }
        }
        selectedTaxi.location.setX(taxiX);
        selectedTaxi.location.setY(taxiY);
        System.out.println("The taxi has arrived!");
    }
    public int[][] getBlockedCoordinates() throws IOException {

       File file = new File ("src/main/java/org/taxiapp/Files/blockedCoords.txt");
       int length = (int) Files.lines(file.toPath()).count();
       int[][] blockedCoordinatesTemp = new int[length][];

        try{
            String line = "";
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int i = 0;
            while ((line = reader.readLine())!= null){
                String [] values = line.split(", ");
                int[] coords = new  int[2];
                coords[0] = Integer.parseInt(values[0]);
                coords[1] = Integer.parseInt(values[1]);
                blockedCoordinatesTemp[i] = coords;
                i++;
            }
        }catch (IOException e){
            System.out.println("Error handling files");
        }
return blockedCoordinatesTemp;
    }
    public boolean isBlocked(int x, int y){
        try{
            File file = new File ("src/main/java/org/taxiapp/Files/blockedCoords.txt");
            String line = "";
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine())!= null){
                String [] values = line.split(", ");
                int[] coords = new  int[2];
                coords[0] = Integer.parseInt(values[0]);
                coords[1] = Integer.parseInt(values[1]);
                if (coords[0] == x && coords[1]== y){
                    return true;
                }

            }
        }catch (IOException e){
            System.out.println("Error handling files");
        }
        return false;
    }
}


