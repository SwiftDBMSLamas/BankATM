package main.com.allan.amca.register;

import main.com.allan.amca.data.Dao;
import main.com.allan.amca.data.DaoFactory;
import main.com.allan.amca.enums.DaoType;
import main.com.allan.amca.user.Client;
import main.com.allan.amca.user.UserDaoImpl;
import main.com.allan.amca.user.UserFactory;
import main.com.allan.amca.data.PINCryptUtils;

/**
 * Allows user to register and create an account. Traditionally user registration is not supported for online banking
 * or for ATMs. The bank will actually provide access, and you would need to create a password. But since we're not
 * your traditional ATM... use this class to create an account.
 * @author allanaranzaso
 */
public class RegisterViewModel {

    private static final RegisterViewModel instance = new RegisterViewModel();
    private static final int NEW_CLIENT_SEND_REQ = 1;
    private static final int SALT_LENGTH = 30;
    // prevent from instantiating this class. See newInstance()
    private RegisterViewModel() {}

    /**
     * Creates an account for the new client. This is essentially what would happen if you opened an account at the bank.
     * Once registration is successful, the new client object is sent statically to the HashMap created, which allows
     * the application to retrieve the client object if the new client decides to make transactions
     * @param firstName the client's first name
     * @param lastName the client's last name
     * @param pin the client's desired pin
     * @return true if the registration completed successfully
     */
    public boolean register(final String firstName, final String lastName, final String pin) {
        Dao<Client, Long> dao = DaoFactory.createDao(DaoType.USER);
        boolean registerSuccess;
        final Client newClient;

        // Validate if PIN is valid... if so, a new user will be created
        validatePin(pin);
        registerSuccess = dao.create(UserFactory.createUser(firstName, lastName));

        if (registerSuccess) {
            newClient = dao.retrieve(UserDaoImpl.getNewClientIDFromDB());
            final String salt = PINCryptUtils.getSalt(SALT_LENGTH); // Create salt value to store with the PIN
            final String securePin = PINCryptUtils.generateSecurePIN(pin, salt); // Generate an encrypted PIN
            PINCryptUtils.updatePinAndSalt(securePin, salt);

            openAccount(newClient);
            Client.sendClient(NEW_CLIENT_SEND_REQ, newClient);
        }
        return registerSuccess;
    }

    private static void validatePin(final String pin) {
        if (pin == null || pin.length() < 4 || pin.isBlank()) {
            throw new IllegalArgumentException("PIN must be minimum 4 digits or more");
        }
    }

    private void openAccount(final Client clientToAdd) {
        Dao<Client, Long> accountDao = DaoFactory.createDao(DaoType.ACCOUNT);
        accountDao.create(clientToAdd);
    }
    // Singleton access
    public static RegisterViewModel newInstance() {
        return instance;
    }
}
