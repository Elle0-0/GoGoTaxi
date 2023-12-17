package org.taxiapp;

import org.junit.jupiter.api.Test;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginManagerTest {
    @Test
    public void testValidInput() {
        assertTrue(LoginManager.isValidInput("password"));
        assertFalse(LoginManager.isValidInput("password "));
    }

    @Test
    public void passwordHashTest() throws NoSuchAlgorithmException {
        String password1 = "ThisIsAPassword";
        String password2 = "ThisIsApassword";
        String password3 = "ThisIsAPassword "; //technically not required cause the user isn't allowed to enter spaces into their password
        assertEquals(LoginManager.passwordHash(password1),LoginManager.passwordHash(password1));
        assertNotEquals(LoginManager.passwordHash(password1),LoginManager.passwordHash(password2));
        assertNotEquals(LoginManager.passwordHash(password1),LoginManager.passwordHash(password3));
    }

    @Test
    public void userChecker() {
        LoginManager.username = "a";
        LoginManager.password = "a";
        assertTrue(LoginManager.userLogin());
    }

    @Test
    public void noUser(){
        LoginManager.username = "thisIsNotAUser";
        LoginManager.password = "Anything";
        LoginManager.userFound = false;
        assertFalse(LoginManager.userLogin());
    }

    @Test
    public void signUpTest() throws NoSuchAlgorithmException {
        String usernameTest ="testUser";
        String passwordTest = "testPassword";
        LoginManager.userSignUp(usernameTest, LoginManager.passwordHash(passwordTest));
        LoginManager.username = usernameTest;
        LoginManager.password = passwordTest;
        assertTrue(LoginManager.userLogin());
    }
}
