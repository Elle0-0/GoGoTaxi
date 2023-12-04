package org.taxiapp;

import java.util.Random;

public class Customer extends User{

    String password;
    Location destination;
    double tip;
    double time;
    String[] experience = {
            "You call for a taxi, and a sleek, luxurious vehicle arrives promptly.\n The interior is immaculate, with plush leather seats and a courteous driver offering bottled water and snacks.\n The ride is smooth, the driver is knowledgeable about the city, and the overall experience feels premium and comfortable.",
            "You book a taxi through a service known for its exceptional customer service.\n The driver arrives early, greets you warmly, and assists with luggage.\n Throughout the journey, the driver engages in pleasant conversation, shares local tips, and ensures a safe and enjoyable ride. \n The service is prompt, and the vehicle is well-maintained.",
            "You hail a taxi on the street. The vehicle is clean, and the driver is polite.\n Although not overly chatty, the driver navigates efficiently through traffic and takes the most direct route.\n It's a standard, satisfactory ride without any major issues.",
            "You hop into a taxi and are greeted by a driver with a genuinely friendly demeanor.\n The driver strikes up a conversation, sharing interesting stories and local insights.\n Despite a slightly longer route due to traffic, the cheerful interaction makes the journey enjoyable.",
            "You book a taxi, but the driver arrives late, without any apology or explanation.\n The vehicle appears poorly maintained, and the driver is impolite and drives recklessly.\n The ride is uncomfortable and stressful, leaving you dissatisfied and wishing for a better experience."
    };


    public void insertDestination() {

    }
    public void getListOfTaxis() {

    }
    public void calculateTimeTaken() {

    }
    public void startJourney() {

    }
    public void tripExperience() {
        Random random = new Random();
        int randomExperience = random.nextInt(experience.length);
        System.out.println(experience[randomExperience]);

    }
    public void  tipTaxi() {

    }
    public void closeApp() {

    }
}
