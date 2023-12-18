package org.taxiapp;
import org.taxiapp.Aesthetics.Colors;
import org.taxiapp.Aesthetics.Icons;


public class Map {
    private final String[][] Map = new String[10][10];

    public String getIcon(int x, int y){
        return Map[x][y];
    }

    public void establishMap() {
        for (String[] row : Map) {
            for (int i = 0; i < row.length; i++) {
                row[i] = " . ";

            }
        }

    }

    public void printMap() {
        for (String[] row : Map) {
            for (String s : row) {
                System.out.print(s);
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean changeCoord(int x, int y, String icon) {
        try {
            Map[x][y] = icon;
        }
        // if somehow the target is off the map, the taxi will still find it without crashing the program
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Hmm, it seems you are off the map.");
            System.out.println("Don't worry, we'll handle it!");
            return false;
        }
        return true;

    }

    public void moveToTarget(Taxi selectedTaxi, int targetX, int targetY, String icon, String pathColour) {

    }
    public void moveToTarget(Taxi selectedTaxi, int targetX, int targetY, String icon){

    }

}


