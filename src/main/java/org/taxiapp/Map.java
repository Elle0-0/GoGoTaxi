package org.taxiapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

public class Map {
    int distance = 0;
    private String[][] Map = new String[10][10];
    public void establishMap(){
        for (String[] row: Map){
            for (int i = 0; i < row.length; i++){
                row[i] = " . ";

            }
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
    public void changeCoord(int x, int y, String icon){
        Map[x][y] = icon;
    }
    public void moveToCustomer(Taxi selectedTaxi, Customer customer){
        int taxiX = selectedTaxi.location.getX();
        int taxiY = selectedTaxi.location.getY();
        int customerX = customer.location.getX();
        int customerY = customer.location.getY();

        changeCoord(customerX, customerY, " \uD83E\uDDCD\uD83C\uDFFB\u200D♀\uFE0F ");

        while ((taxiX != customerX) && (taxiY != customerY)){
            if (taxiX < customerX){
                while (taxiX != customerX){
                    taxiX ++ ;
                    changeCoord(taxiX, taxiY, selectedTaxi.getIcon());
                    changeCoord((taxiX-1), taxiY, " + ");
                    printMap();
                    System.out.println();
                    distance ++;
                }
            } else if (taxiX > customerX){
                while (taxiX != customerX){
                    taxiX -- ;
                    changeCoord(taxiX, taxiY, selectedTaxi.getIcon());
                    changeCoord((taxiX+1), taxiY, " + ");
                    printMap();
                    System.out.println();
                    distance ++;
                }
            }
            if (taxiY < customerY){
                while (taxiY != customerY){
                    taxiY ++ ;
                    changeCoord(taxiX, taxiY, selectedTaxi.getIcon());
                    changeCoord(taxiX, (taxiY-1), " + ");
                    printMap();
                    System.out.println();
                    distance ++;
                }
            } else if (taxiY > customerY){
                while (taxiY != customerY){
                    taxiY -- ;
                    changeCoord(taxiX, taxiY, selectedTaxi.getIcon());
                    changeCoord(taxiX, (taxiY+1), " + ");
                    printMap();
                    System.out.println();
                    distance ++;
                }

            }
        }
}
    public int getDistanceTravelled() {
        return distance;
    }
    public static void main(String[] args) {
        Map m = new Map();
        m.establishMap();
        m.printMap();
        Taxi t = new Taxi();
        t.location.setX(3);
        t.location.setY(4);
        Customer c = new Customer();
        c.location.setX(2);
        c.location.setY(8);
        m.moveToCustomer(t,c);
        System.out.println(m.distance);
    }

}
