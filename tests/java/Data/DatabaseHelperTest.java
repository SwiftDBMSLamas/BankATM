package Data;

import com.allan.amca.user.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseHelperTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void onCreate() {
    }

    @Test
    void addClient() {
        final DatabaseHelper db = new DatabaseHelper();
        final Client client = new Client("Allan", "Aranzaso", "allanaranzaso");
        db.addClient(client);

        assertTrue(db.checkIfClientExists(client));
    }

    @Test
    void updateClient() {
    }

    @Test
    void deleteClient() {
    }
}