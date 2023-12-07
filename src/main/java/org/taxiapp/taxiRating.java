package org.taxiapp;

import java.io.*;

public class taxiRating extends Taxi{

    double rating;
    String filePath = "src/main/java/org/taxiapp/Files/taxiRating.txt";

    public void setRating(Taxi taxi, Customer customer) {
        int customerRating = customer.getRating();
        String line;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(customerRating);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void getAverageRating(){
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

                System.out.println(counter + " " + sum);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public double getRating() {
        return rating;
    }

    public void setTaxiRating() {
        getName();
        getRating();
    }

    public static void main(String[] args) {
        taxiRating t = new taxiRating();
        t.getAverageRating();
    }
}
