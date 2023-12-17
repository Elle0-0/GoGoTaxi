package org.taxiapp;

import org.taxiapp.Aesthetics.Colors;
import org.taxiapp.Aesthetics.Icons;
import org.taxiapp.TaxiManagement.VehicleTypes;

import java.io.IOException;
import java.lang.Math;
import java.util.Scanner;

public class VehicleHiring {
    private Taxi[] possibleTaxis; // contains 70 taxis
    private Taxi[] currentTaxis; // will contain 20 taxis
    private CustomLinkedList availableTaxisList;  // contains how many taxis will be available to the taxi
    private CustomLinkedList<String> names;
    private Map worldMap;
    private int taxiRange;
    private Taxi chosenTaxi;


    public void setChosenTaxi(Taxi chosenTaxi) {
        this.chosenTaxi = chosenTaxi;
    }

    public Taxi getChosenTaxi() {
        return chosenTaxi;
    }

    public CustomLinkedList getAvailableTaxisList() {
        return availableTaxisList;
    }

    public Map getWorldMap() {
        return worldMap;
    }

    public CustomLinkedList<String> getNames() {
        return names;
    }

    public VehicleHiring() throws IOException {
        possibleTaxis = new Taxi[70];
        currentTaxis = new Taxi[20];
        availableTaxisList = new CustomLinkedList();
        names = new CustomLinkedList<String>();
        worldMap = new Map();
        worldMap.establishMap();
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
        /**the usedNumbers list is to ensure if there was a repeat assigning of information
         * in the possible taxis list, it wont be repeated in the current taxi list
         **/
        CustomLinkedList<Integer> usedNumbers = new CustomLinkedList<>();
        for (int i = 0; i < currentTaxis.length; i++) {
            while (currentTaxis[i] == null) {
                int random = (int) (Math.random() * (possibleTaxis.length - 1));
                if (!usedNumbers.contains(random)) {
                    currentTaxis[i] = possibleTaxis[random];
                }
            }
        }
        // move the taxis from the original position before they are selected
        moveTaxis();
    }

    public void moveTaxis() {
        // moves the taxis before the are selected, simulates real life
        int random = (int) (Math.random() * (10));
        for (int i = 0; i < random; i++) {
            for (Taxi taxi : possibleTaxis) {
                taxi.loopedMovement();
            }
        }
    }

    public Taxi getTaxisInRange(Customer customer, VehicleTypes taxiType) {

        int customerX = customer.location.getX();
        int customerY = customer.location.getY();
        // puts the user visibly on the map
        worldMap.changeCoord(customerX, customerY, Icons.person);


        for (Taxi taxi : currentTaxis) {
            int taxiX = taxi.location.getX();
            int taxiY = taxi.location.getY();
            // the next two lines calulate whether the taxi is within the 5 unit radius from the user
            double perpDistance = Math.sqrt(Math.pow((customerX - taxiX), 2) + Math.pow((customerY - taxiY), 2));
            if (!(perpDistance >= taxiRange) && taxiRange > 0 && customerX != taxiX && customerY != taxiY) {
                // if they are and they are not already in the list off available taxis
                if (!names.contains(taxi.getName()) && taxi.getTaxi().getVehicleType().equals(taxiType)) {
                    // add the user to list of available taxis
                    availableTaxisList.sortInsert(perpDistance, taxi);
                    worldMap.changeCoord(taxiX, taxiY, Icons.allcars);
                    // adds their name to prevent duplication
                    names.simpleInsert(taxi.getName());
                }
            }
        }

        // if there are no taxis in range, expand range by 1
        if (availableTaxisList.size == 0) {
            taxiRange++;
            getTaxisInRange(customer, taxiType);
        } else {// display taxi information for the user by the nearest
            availableTaxisList.printList();
            worldMap.printMap();
            // takes the user choice
            Scanner scanner = new Scanner(System.in);
            int userChoice = scanner.nextInt();
            setChosenTaxi((Taxi) availableTaxisList.getChosenTaxi(userChoice - 1));
        }
        return chosenTaxi;
    }

    public Taxi getATaxi(Customer customer) {
        // assigns the user a taxi
        initialiseTaxis();
        VehicleTypes taxiType = preferredTaxiType();
        System.out.println("Please choose a taxi");
        System.out.println("The Taxi's nearest to you are: ");
        return getTaxisInRange(customer, taxiType);
    }

    public VehicleTypes preferredTaxiType() {
        Scanner scanner = new Scanner(System.in);
        VehicleTypes vehicle = null;
        System.out.println("Please chose the vehicle Type that you would like");
        System.out.println("[1]Premium \n[2]Regular");
        boolean valid = false;
        while (!valid) {
            int vehicleChoice = scanner.nextInt();
            if (vehicleChoice == 1) {
                vehicle = VehicleTypes.PREMIUM;
                System.out.println("Preferred Taxi Type: " + Colors.italicStart + "PREMIUM" + Colors.italicEnd);
                valid = true;
                return VehicleTypes.PREMIUM;
            } else if (vehicleChoice == 2) {
                vehicle = VehicleTypes.REGULAR;
                System.out.println("Preferred Taxi Type: " + Colors.italicStart + "REGULAR" + Colors.italicEnd);
                valid = true;
                return VehicleTypes.REGULAR;

            } else {
                System.out.println("Invalid input. Try again!");
            }
        }
        return null;
    }

}




