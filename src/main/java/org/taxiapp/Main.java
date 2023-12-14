package org.taxiapp;

import org.taxiapp.Aesthetics.Icons;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
        Program myProgram = new Program();
        myProgram.launch();
    }
}