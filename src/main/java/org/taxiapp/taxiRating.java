package org.taxiapp;

import java.io.*;
import java.text.DecimalFormat;

public class taxiRating extends Taxi{

    double rating;
    String filePath = "src/main/java/org/taxiapp/Files/taxiRating.txt";
    DecimalFormat df = new DecimalFormat("#.#");

    public void setRating(Taxi taxi, Customer customer) {
        int customerRating = customer.getRating();
        String line;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(customerRating);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public double getAverageRating(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                double counter = 0.0;
                double sum = 0.0;
                double avgValue;
                String[] data = line.split(",");
                counter += data.length;
                for (String value : data) {
                    sum += Double.parseDouble(value);
                }
                double storeRating = sum/counter;
                System.out.println(df.format(storeRating));
                rating = Double.parseDouble(df.format(storeRating));
            }
        return rating;
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
        getAverageRating();
        return rating;
    }

    public static void main(String[] args) {
        taxiRating t = new taxiRating();
        t.getAverageRating();
    }
}
