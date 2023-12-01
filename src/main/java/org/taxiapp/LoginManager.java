package org.taxiapp;

import java.io.*;
import java.util.Scanner;

public class LoginManager {

    static String username;
    static String password;
    static Scanner input = new Scanner(System.in);

    static String filePath = "src/main/java/org/taxiapp/resources/userData.csv";
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

    public static void customerSignUp() {
        System.out.println("Enter username: ");
        username = input.nextLine();
        System.out.println("Enter password: ");
        password = input.nextLine();
        userSignUp(username, password);
    }

    public static void customerLogin() {
        System.out.println("Enter username: ");
        username = input.nextLine();
        System.out.println("Enter password: ");
        password = input.nextLine();
        userLogin();
    }

    public static void userLogin() {
        String line;
        boolean userFound = false;
        try(BufferedReader user = new BufferedReader(new FileReader(filePath))) {
            user.readLine();
            while ((line = user.readLine()) != null) {
                String[] data = line.split(",");
                if(username.equals(data[0]) && password.equals(data[1])) {
                    System.out.println("user exists");
                    userFound = true;
                    break;
                }
            }
            if (!userFound) {
                System.out.println("user does not exist.");
            }

        } catch (IOException e) {
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
                System.out.println(username + " " + password);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
