package org.taxiapp;

import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;

public class taxiRating extends Taxi{

    double rating;
    String filePath = "src/main/java/org/taxiapp/Files/taxiRating.txt";
    String taxiFilePath = "src/main/java/org/taxiapp/Files/taxiInformation.txt";
    DecimalFormat df = new DecimalFormat("#.#");

    @Override
    public void setRating(Taxi taxi, Customer customer) {
        int customerRating = customer.getRating();
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(taxiFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(taxiFilePath, true));
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                if (taxi.getTaxi().getCarReg().equals(data[2])) {
                    writer.write(customerRating);
                    writer.write(", ");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public double getAverageRating(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(taxiFilePath))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                double counter = 0.0;
                double sum = 0.0;
                double avgValue;
                String[] data = line.split(",");
                counter += data.length - 5;
                for (int i = 5; i <data.length; i++) {
                    sum += Double.parseDouble(data[i]);
                }
                avgValue = sum/counter;
                System.out.println(df.format(avgValue));
                rating = Double.parseDouble(df.format(avgValue));
            }
        return rating;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readTaxiRatings() {
        String line;
        String carReg = null;
        String carReg2 = null;
        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(filePath));
            reader1.readLine();
            while( (line = reader1.readLine()) != null ) {
                String[] data = line.split(",");
                carReg = data[0];
            }
            BufferedReader reader2 = new BufferedReader(new FileReader(taxiFilePath));
            reader2.readLine();
            while((line = reader2.readLine()) != null) {
                String[] datao = line.split(",");
                carReg2 = datao[2];
            }
            if (carReg.equals(carReg2)) {
                System.out.println(carReg + carReg2);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public double getRating() {
        return rating;
    }


    //seems unnecessary rn.
    public double setTaxiRating() {
        getName();
        getRating();
//        getAverageRating();
        return rating;
    }

    public static void main(String[] args) {
        taxiRating t = new taxiRating();
        t.getAverageRating();
        //t.readTaxiRatings();
    }

}
