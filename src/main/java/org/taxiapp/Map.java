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
        establishMap();
        int taxiX = selectedTaxi.location.getX();
        int taxiY = selectedTaxi.location.getY();
        changeCoord(targetX, targetY, icon);
        // while the taxi is not at it's target
        while ((taxiX != targetX) && (taxiY != targetY)) {
            // if the taxi is to the left of the target
            if (taxiY < targetY) {
                //until they are at the right y coordinate
                while (taxiY != targetY) {
                    // move the taxi and show the movement with the icon
                    taxiY++;
                    changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                    // change the previous icon to show the path change
                    changeCoord(taxiX, (taxiY - 1), (pathColour + " + " + Colors.reset));
                    printMap();
                    System.out.println();
                }
            }
            // if the taxi is to the right of the target
            else if (taxiY > targetY) {
                while (taxiY != targetY) {
                    taxiY--;
                    changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                    changeCoord(taxiX, (taxiY + 1),(pathColour + " + " + Colors.reset));
                    printMap();
                    System.out.println();
                }

            }
            // is the taxi is below the target
            if (taxiX < targetX) {
                while (taxiX != targetX) {
                    taxiX++;
                    // ensure it can't go out of bounds
                    changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                    changeCoord((taxiX - 1), taxiY, (pathColour + " + " + Colors.reset));
                    printMap();
                    System.out.println();
                }
            }
            // if the taxi is above the target
            else if (taxiX > targetX) {
                while (taxiX != targetX) {
                    taxiX--;
                    changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                    changeCoord((taxiX + 1), taxiY, (pathColour + " + " + Colors.reset));
                    printMap();
                    System.out.println();
                }
            }
        }
        // hold the taxis new location
        selectedTaxi.location.setX(taxiX);
        selectedTaxi.location.setY(taxiY);
        System.out.println("The taxi has arrived!");
    }
}


