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

public class PINCryptUtils {

    private static final Random RANDOM = new SecureRandom();
    private static final String DIGITS = "0123456789";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

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
            SecretKeyFactory secretKeyF = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return secretKeyF.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing pin:" + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public static String generateSecurePIN(final String pin, final String salt) {
        String retVal = null;

        byte[] securePIN = hash(pin.toCharArray(), salt.getBytes());
        retVal = Base64.getEncoder().encodeToString(securePIN);
        return retVal;
    }

    public static boolean verifyPIN(final String providedPIN, final String securePIN, final String salt) {
        boolean retVal = false;

        final String newSecurePIN = generateSecurePIN(providedPIN, salt);
        retVal = newSecurePIN.equalsIgnoreCase(securePIN);
        return retVal;
    }

    public static boolean updatePinAndSalt(String securePin, String salt) {
        final String URI            = DataResources.getDBUri();
        final String DB_USER        = DataResources.getDBUsername();
        final String DB_PW          = DataResources.getDBPassword();
        final int recordsUpdated;
        final String UPDATE_PIN_SALT_QUERY = "UPDATE clients SET pin = ?, salt = ? WHERE client_id = ?";
        boolean updateSuccess = false;

        try (Connection connection = DriverManager.getConnection(URI, DB_USER, DB_PW)) {
            try (PreparedStatement updatePinStmt = connection.prepareStatement(UPDATE_PIN_SALT_QUERY)) {
                connection.setAutoCommit(false);
                updatePinStmt.setString(1, securePin);
                updatePinStmt.setString(2, salt);
                updatePinStmt.setLong(3, UserDaoImpl.getNewClientIDFromDB());

                recordsUpdated = updatePinStmt.executeUpdate();
                if (recordsUpdated > 0) {
                    updateSuccess = true;
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return updateSuccess;
    }
}
