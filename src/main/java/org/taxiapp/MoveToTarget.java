package org.taxiapp;

import org.taxiapp.Aesthetics.Colors;
import org.taxiapp.Aesthetics.Icons;

public class MoveToTarget extends Map {

    @Override
    public void moveToTarget(Taxi selectedTaxi, int targetX, int targetY, String icon, String pathColour) {
        establishMap();
        int taxiX = selectedTaxi.location.getX();
        int taxiY = selectedTaxi.location.getY();
        changeCoord(targetX, targetY, icon);
        // while the taxi is not at it's target
        while (taxiX != targetX || taxiY != targetY) {
            // if the taxi is to the left of the target
            if (taxiY < targetY) {
                //until they are at the right y coordinate
                while (taxiY != targetY) {
                    // move the taxi and show the movement with the icon
                    taxiY++;
                    changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                    // change the previous icon to show the path change
                    changeCoord(taxiX, (taxiY - 1), (pathColour + " + " + Colors.reset));
                    selectedTaxi.setKmTravelled(selectedTaxi.getKmTravelled() + 1);
                    printMap();

                }
            }
            // if the taxi is to the right of the target
            else if (taxiY > targetY) {
                while (taxiY != targetY) {
                    taxiY--;
                    changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                    changeCoord(taxiX, (taxiY + 1),(pathColour + " + " + Colors.reset));
                    printMap();
                    selectedTaxi.setKmTravelled(selectedTaxi.getKmTravelled() + 1);
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
                    selectedTaxi.setKmTravelled(selectedTaxi.getKmTravelled() + 1);
                }
            }
            // if the taxi is above the target
            else if (taxiX > targetX) {
                while (taxiX != targetX) {
                    taxiX--;
                    changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                    changeCoord((taxiX + 1), taxiY, (pathColour + " + " + Colors.reset));
                    printMap();
                        selectedTaxi.setKmTravelled(selectedTaxi.getKmTravelled() + 1);
                }
            }
        }
        // hold the taxis new location
        selectedTaxi.location.setX(taxiX);
        selectedTaxi.location.setY(taxiY);
        System.out.println("The taxi has arrived!");
    }
    @Override
    public void moveToTarget(Taxi selectedTaxi, int targetX, int targetY, String icon) {
        String pathColour = Colors.blue;
        establishMap();
        int taxiX = selectedTaxi.location.getX();
        int taxiY = selectedTaxi.location.getY();
        changeCoord(targetX, targetY, icon);
        // while the taxi is not at it's target
        while (taxiX != targetX || taxiY != targetY) {
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

                }
            }
            // if the taxi is to the right of the target
            else if (taxiY > targetY) {
                while (taxiY != targetY) {
                    taxiY--;
                    changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                    changeCoord(taxiX, (taxiY + 1),(pathColour + " + " + Colors.reset));
                    printMap();

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
                }
            }
            // if the taxi is above the target
            else if (taxiX > targetX) {
                while (taxiX != targetX) {
                    taxiX--;
                    changeCoord(taxiX, taxiY, Icons.chosenTaxi);
                    changeCoord((taxiX + 1), taxiY, (pathColour + " + " + Colors.reset));
                    printMap();
                }
            }
        }
        // hold the taxis new location
        selectedTaxi.location.setX(taxiX);
        selectedTaxi.location.setY(taxiY);
        System.out.println("The taxi has arrived!");
    }
}
