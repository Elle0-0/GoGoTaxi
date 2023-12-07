package org.taxiapp;

import org.taxiapp.Aesthetics.Icons;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;

public class VehicleHiring {
    Taxi[] possibleTaxis; // contains 70 taxis
    Taxi[] currentTaxis; // will contain 20 taxis
    ArrayList<Taxi> availableTaxis; // contains how many taxis will be available to the taxi
    Map worldMap;
    int taxiRange;
    Taxi chosenTaxi;

    public VehicleHiring() {
        possibleTaxis = new Taxi[70];
        currentTaxis = new Taxi[20];
        availableTaxis = new ArrayList<>();
        worldMap = new Map();
        worldMap.establishMap();
        taxiRange = 5;
    }

    public void initialiseTaxis(){
        // first create 70 null taxis
        for (int i = 0; i < possibleTaxis.length ; i++){
            possibleTaxis[i] = new Taxi();
        }
        // next randomly assign these 70 taxis information
        for (Taxi taxi : possibleTaxis) {
            taxi.assignRandomInformation();
        }
        // chose 20 random taxis from that list to be used in the current iteration
        ArrayList<Integer> usedNumbers= new ArrayList<Integer>();
        for (int i = 0; i < currentTaxis.length; i ++){
            while (currentTaxis[i] == null){
                int random = (int) (Math.random() * (possibleTaxis.length -1) );
                if (!usedNumbers.contains(random)){
                    currentTaxis[i] = possibleTaxis[random];
                }
            }
        }
    }

    public void getTaxisInRange(Customer customer){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose a taxi");
        int customerX = customer.location.getX();
        int customerY = customer.location.getY();
        worldMap.changeCoord(customerX, customerY, Icons.person);
        ArrayList<String> names = new ArrayList<>();
        int i = 1;
        for (Taxi taxi: currentTaxis){
            int taxiX = taxi.location.getX();
            int taxiY = taxi.location.getY();
            double perpDistance = Math.sqrt(Math.pow((customerX - taxiX), 2) + Math.pow((customerY - taxiY), 2));
            if (!(perpDistance >= taxiRange) && taxiRange != 0) {
                if (names.contains(taxi.getName()) == false){
                    availableTaxis.add(taxi);
                    System.out.println("["+i+"]");
                    i++;
                    taxi.displayInformation();
                    worldMap.changeCoord(taxiX, taxiY, Icons.allcars);
                    System.out.println();
                    names.add(taxi.getName());

                }


            }
        }if (availableTaxis.size() == 0){
            taxiRange ++;
            getTaxisInRange(customer);
        }

        worldMap.printMap();
        int userChoice = scanner.nextInt();
        chosenTaxi = availableTaxis.get(userChoice-1);


    }
    public Taxi getATaxi(Customer customer){
        initialiseTaxis();
        getTaxisInRange(customer);
        return chosenTaxi;


    }



    }

