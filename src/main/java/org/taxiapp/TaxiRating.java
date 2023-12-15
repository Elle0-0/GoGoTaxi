package org.taxiapp;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class TaxiRating extends Taxi{

    double avgRating;
    String filePath = "src/main/java/org/taxiapp/Files/taxiRating.txt";
    String taxiFilePath = "src/main/java/org/taxiapp/Files/taxiInformation.txt";
    DecimalFormat df = new DecimalFormat("#.#");

    @Override
    public void setRating(Taxi taxi, Customer customer) {
        int customerRating = customer.getRating();
        String line;
        ArrayList<String> updatedData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(taxiFilePath))) {

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                String reg = data[2];
                if (taxi.getTaxi().getCarReg().equals(reg)) {
                    data[data.length - 1] += ", " + customerRating;
                    line = String.join(", ", data);
                    //getAverageRating(taxi);
                }
                updatedData.add(line);
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(taxiFilePath))) {
                for (String newData : updatedData) writer.write(newData + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public double getAverageRating(Taxi taxi){
        String line;
        double avgValue=0;
        try (BufferedReader reader = new BufferedReader(new FileReader(taxiFilePath))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                double counter = 0.0;
                double sum = 0.0;
                String[] data = line.split(", ");
                String reg = data[2];
                if (taxi.getTaxi().getCarReg().equals(reg)) {
                    for (int i = 5; i < data.length; i++) {
                        sum += Double.parseDouble(data[i].trim());
                        counter++;
                    }
                    avgValue = sum / counter;
                }
            }
            //System.out.println(avgValue);
            System.out.println(df.format(avgValue));
            avgRating = Double.parseDouble(df.format(avgValue));
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
                String[] data1 = line.split(",");
                carReg = data1[0];
            }
            BufferedReader reader2 = new BufferedReader(new FileReader(taxiFilePath));
            reader2.readLine();
            while((line = reader2.readLine()) != null) {
                String[] data2 = line.split(",");
                carReg2 = data2[2];
            }
            if (carReg.equals(carReg2)) {
                System.out.println(carReg + carReg2);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws IOException {
        TaxiRating t = new TaxiRating();
        Customer c = new CustomerLocation();
        t.getAverageRating(t);
        t.readTaxiRatings();
    }

}
