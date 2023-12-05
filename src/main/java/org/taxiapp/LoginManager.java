package org.taxiapp;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class LoginManager {

    static String username;
    static String password;
    static Scanner input = new Scanner(System.in);
    static boolean userFound = false;
    static boolean validInput = false;

    static String filePath = "src/main/java/org/taxiapp/resources/userData.csv";

    public static boolean isValidInput(String string) {
        return !string.contains(" ");
    }
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
            validInput = true;
            userSignUp(username, passwordHash(password));
        }
        customerLogin();
    }

    private static String passwordHash(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] passwordBytes = password.getBytes();
        byte[] hashBytes = digest.digest(passwordBytes);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashBytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }

    public static void customerLogin() {
        System.out.println("------USER LOGIN------");
        while (!userFound) {
            System.out.println("Enter username: ");
            username = input.nextLine();
            System.out.println("Enter password: ");
            password = input.nextLine();
            userLogin();
            if (!userFound) {
                System.out.println("Do you want to try again?(yes/no): ");
                String answer = input.nextLine();
                if (!answer.equalsIgnoreCase("yes")) {
                    break;
                }
            }
        }
    }

    public static void userLogin() {
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
                }


        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getAllUsers() {
        String username;
        String password;
        String line = "";
        try(BufferedReader user = new BufferedReader(new FileReader(filePath))) {
            user.readLine();
            while ((line = user.readLine()) != null) {
                String[] data = line.split(",");
                username = data[0];
                password = data[1];
                System.out.println(username);
                //System.out.println(password); //password not required to be printed but i have established it anyways.
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUsername() {
        return username;
    }
}