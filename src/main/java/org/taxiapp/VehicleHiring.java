package org.taxiapp;

import org.taxiapp.Aesthetics.Icons;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;

public class VehicleHiring {
    Taxi[] possibleTaxis; // contains 70 taxis
    Taxi[] currentTaxis; // will contain 20 taxis
    //ArrayList<Taxi> availableTaxis; // contains how many taxis will be available to the taxi
    ownList availableTaxisList;
    Map worldMap;
    int taxiRange;
    Taxi chosenTaxi;
    boolean taxiNeeded;

    public VehicleHiring() throws IOException {
        possibleTaxis = new Taxi[70];
        currentTaxis = new Taxi[20];
        //availableTaxis = new ArrayList<>();
        availableTaxisList = new ownList();
        worldMap = new Map();
        worldMap.establishMap();
        taxiNeeded = false;
        taxiRange = 5;
    }

    public void initialiseTaxis() {
        // first create 70 null taxis
        for (int i = 0; i < possibleTaxis.length; i++) {
            possibleTaxis[i] = new Taxi();
        }
        // next randomly assign these 70 taxis information
        for (Taxi taxi : possibleTaxis) {
            taxi.assignRandomInformation();
        }
        // chose 20 random taxis from that list to be used in the current iteration
        //ArrayList<Integer> usedNumbers = new ArrayList<Integer>();
        ownList<Integer> usedNumbers = new ownList<>();
        for (int i = 0; i < currentTaxis.length; i++) {
            while (currentTaxis[i] == null) {
                int random = (int) (Math.random() * (possibleTaxis.length - 1));
                if (!usedNumbers.contains(random)) {
                    currentTaxis[i] = possibleTaxis[random];
                }
            }
        }
    }

    public void moveTaxis() {
        while (!taxiNeeded) {
            for (Taxi taxi : possibleTaxis) {
                taxi.randomMovement();
            }
        }
    }

    public Taxi getTaxisInRange(Customer customer) {
        taxiNeeded = true;
        Scanner scanner = new Scanner(System.in);

        int customerX = customer.location.getX();
        int customerY = customer.location.getY();
        worldMap.changeCoord(customerX, customerY, Icons.person);
        //ArrayList<String> names = new ArrayList<>();
        ownList<String> names = new ownList<>();

        int i = 1;
        for (Taxi taxi : currentTaxis) {
            int taxiX = taxi.location.getX();
            int taxiY = taxi.location.getY();
            double perpDistance = Math.sqrt(Math.pow((customerX - taxiX), 2) + Math.pow((customerY - taxiY), 2));
            if (!(perpDistance >= taxiRange) && taxiRange > 0 && customerX != taxiX && customerY != taxiY) {
                if (names.contains(taxi.getName()) == false) {
                    availableTaxisList.sortInsert(perpDistance, taxi);
                    worldMap.changeCoord(taxiX, taxiY, Icons.allcars);
                    names.simpleInsert(taxi.getName());
                }
            }
        }
        // if there are no taxis in range, expand range
        if (availableTaxisList.size == 0) {
            taxiRange++;
            getTaxisInRange(customer);
        }
        // display taxi information
        availableTaxisList.printList();
        worldMap.printMap();


        int userChoice = scanner.nextInt();
        Taxi chosenTaxi = (Taxi) availableTaxisList.getChosenTaxi(userChoice);
        return chosenTaxi;
    }

public Taxi getATaxi(Customer customer) {
    initialiseTaxis();
    System.out.println("Please choose a taxi");
    System.out.println("Taxi's are sorted by nearest");
    Taxi chosenTaxi = getTaxisInRange(customer);
    return chosenTaxi;


}
}




