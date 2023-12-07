import org.junit.jupiter.api.Test;
import org.taxiapp.LoginManager;

import static org.junit.jupiter.api.Assertions.*;

public class LoginManagerTest {
    @Test
public void testValidInput() {
        assertTrue(LoginManager.isValidInput("password"));
        assertFalse(LoginManager.isValidInput("password "));

    }
}
