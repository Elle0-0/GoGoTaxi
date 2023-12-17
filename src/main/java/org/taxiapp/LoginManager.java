package org.taxiapp;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class LoginManager {

    /** Controls the reading and writing to the csv file, also handles password hashing */
    static String username;
    static String password;
    static Scanner input = new Scanner(System.in);
    static boolean userFound = false;
    static boolean validInput = false;
    static String filePath = "src/main/java/org/taxiapp/resources/userData.csv";

    //checks if input contains spaces.
    public static boolean isValidInput(String string) {
        return !string.contains(" ");
    }

    //gets customer username.
    public static String getUsername() {
        return username;
    }

    //writes to the csv file with user information. NOTE: this method is not to be called directly
    // it is called in the customer sign up.
    public static void userSignUp(String username, String password) {
        try (BufferedWriter user = new BufferedWriter(new FileWriter(filePath, true)) ) {
            user.append(username);
            user.append(",");
            user.append(password);
            user.append(",");
            user.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //checks if signup information is valid then calls the userSignUp() method to write the user to the file.
    public static void customerSignUp() throws NoSuchAlgorithmException {
        System.out.println("------USER SIGN UP------");
        while (!validInput) {
            System.out.println("Enter username: ");
            username = input.nextLine();
            if (!isValidInput(username)) {
                System.out.println("Username cannot contain spaces, try again.");
                continue;
            }
            System.out.println("Enter password: ");
            password = input.nextLine();
            if (!isValidInput(password)) {
                System.out.println("Password cannot contain spaces, try again.");
                validInput = false;
                continue;
            }
            if (username.isEmpty() || password.isEmpty()) continue;
            validInput = true;
            userSignUp(username, passwordHash(password));
        }
        customerLogin();
    }

    //secure one way password hashing.
    static String passwordHash(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] passwordBytes = password.getBytes();
        byte[] hashBytes = digest.digest(passwordBytes);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashBytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }

    //Prompts the customer to login and calls the userLogin() method. Checks if entered inputs are valid.
    public static void customerLogin() throws NoSuchAlgorithmException {
        System.out.println("------USER LOGIN------");
        while (!userFound) {
            System.out.println("Enter username: ");
            username = input.nextLine();
            System.out.println("Enter password: ");
            password = input.nextLine();
            if (username.isEmpty() || password.isEmpty()) continue;
            userLogin();
            if (!userFound) {
                while (true) {
                    System.out.println("Press enter to try again OR 1 to sign up.");
                    String answer = input.nextLine().trim();
                    if (answer.isEmpty()) {
                        break;
                    }else if (answer.equals("1")) {
                        customerSignUp();
                        break;
                    } else {
                        System.out.println("invalid input. Try again.");
                    }
                }
            }
        }
    }

    //reads the csv file and looks for the entered user details. NOTE: this function is not supposed
    // to be called directly, it is called through the customer log in.
    public static boolean userLogin() {
        String line;
        try(BufferedReader user = new BufferedReader(new FileReader(filePath))) {
            user.readLine();
                while ((line = user.readLine()) != null) {
                    String[] data = line.split(",");
                    if (username.equals(data[0]) && passwordHash(password).equals(data[1])) {
                        System.out.println("user exists");
                        userFound = true;
                        break;
                    }
                }
                if (!userFound) {
                    System.out.println("user does not exist.");
                    return false;
                }
                return true;
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}