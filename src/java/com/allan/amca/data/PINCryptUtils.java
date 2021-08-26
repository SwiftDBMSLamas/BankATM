package com.allan.amca.data;

import com.allan.amca.user.UserDaoImpl;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

/**
 * Create and encrypt a PIN for the application using Base64 to encode the String
 */
public class PINCryptUtils {

    private static final Random RANDOM = new SecureRandom();
    private static final String DIGITS = "0123456789";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int ENCRYPTED_PIN_PARAM = 1;
    private static final int SALT_PARAM = 2;
    private static final int CLIENT_ID_PARAM = 3;
    private static final int NO_RECORDS_UPDATED = 0;

    /**
     * Create salt value
     * @param length the length of the salt value you wish to use.
     * @return the salt value
     */
    public static String getSalt(final int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        }
        return new String(returnValue);
    }

    private static byte[] hash(char[] pin, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(pin, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(pin, Character.MIN_VALUE);
        try {
            SecretKeyFactory secretKeyF = SecretKeyFactory.getInstance(ALGORITHM);
            return secretKeyF.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing pin:" + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    /**
     * Generates a secure pin by taking the pin argument passed through and the salt value, which then
     * encodes the String using Base64 and returns that value to store
     * @param pin the pin of the user to encrypt in the database
     * @param salt the salt value
     * @return the encrypted value to store in the database
     */
    public static String generateSecurePIN(final String pin, final String salt) {
        final String retVal;

        byte[] securePIN = hash(pin.toCharArray(), salt.getBytes());
        retVal = Base64.getEncoder().encodeToString(securePIN);
        return retVal;
    }

    /**
     * Verifies if the PIN provided by the user matches what is stored in the database. Uses the generateSecurePIN method
     * to decrypt and verify if the PIN matches the encryption in the database
     * @param providedPIN the PIN that the user provided
     * @param securePIN the encrypted PIN that is stored in the database
     * @param salt the salt value that is stored in the database with the encrypted PIN
     * @return true if the PIN is a match. Otherwise, false
     */
    public static boolean verifyPIN(final String providedPIN, final String securePIN, final String salt) {
        final boolean retVal;

        final String newSecurePIN = generateSecurePIN(providedPIN, salt);
        retVal = newSecurePIN.equalsIgnoreCase(securePIN);
        return retVal;
    }

    /**
     * Write to database the encrypted PIN and the salt value.
     * @param securePin The encrypted PIN to store in the database
     * @param salt The salt value to store in the database
     */
    public static void updatePinAndSalt(String securePin, String salt) {
        final String URI            = DataResources.getDBUri();
        final String DB_USER        = DataResources.getDBUsername();
        final String DB_PW          = DataResources.getDBPassword();
        final int recordsUpdated;
        final String UPDATE_PIN_SALT_QUERY = "UPDATE clients SET pin = ?, salt = ? WHERE client_id = ?";

        try (Connection connection = DriverManager.getConnection(URI, DB_USER, DB_PW)) {
            try (PreparedStatement updatePinStmt = connection.prepareStatement(UPDATE_PIN_SALT_QUERY)) {
                connection.setAutoCommit(false);

                updatePinStmt.setString(ENCRYPTED_PIN_PARAM, securePin);
                updatePinStmt.setString(SALT_PARAM, salt);
                updatePinStmt.setLong(CLIENT_ID_PARAM, UserDaoImpl.getNewClientIDFromDB());

                recordsUpdated = updatePinStmt.executeUpdate();
                if (recordsUpdated == NO_RECORDS_UPDATED) {
                    throw new IllegalArgumentException("There was an error updating the PIN in the db");
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
