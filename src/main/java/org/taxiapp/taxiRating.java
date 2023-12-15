package org.taxiapp;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class taxiRating extends Taxi{

    double avgRating;
    String filePath = "src/main/java/org/taxiapp/Files/taxiRating.txt";
    String taxiFilePath = "src/main/java/org/taxiapp/Files/taxiInformation.txt";
    DecimalFormat df = new DecimalFormat("#.#");

    @Override
    public void setRating(Taxi taxi, Customer customer) {
        int customerRating = customer.getRating();
        String line;
        //ArrayList<String> reg = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(taxiFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(taxiFilePath, true));
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                String reg = data[2];
                if (taxi.getTaxi().getCarReg().equals(reg)) {
                    writer.append(", ");
                    writer.append(String.valueOf(customerRating));
                }
            }
            writer.close();
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
                avgRating = Double.parseDouble(df.format(avgValue));
            }
        return avgRating;
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


    //seems unnecessary rn.
    public double setTaxiRating() {
        getName();
        //getRating();
//        getAverageRating();
        return 0.0;
    }

    public static void main(String[] args) {
//        taxiRating t = new taxiRating();
//        Customer c = new CustomerLocation();
//        t.getAverageRating();
        //t.readTaxiRatings();
    }

}
