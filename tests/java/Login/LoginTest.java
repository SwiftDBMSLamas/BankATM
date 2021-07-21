package Login;

import com.allan.amca.Login;
import Data.DatabaseHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void login() {
        final DatabaseHelper db = new DatabaseHelper();
        final Login login = Login.getInstance();
//        expected, actual
        assertFalse(login.login("4519011123012301", "pass2"));
        assertTrue(login.login("4519011123012301", "testing1pass"));

    }
}