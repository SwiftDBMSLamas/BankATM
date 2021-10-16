package com.allan.amca.data;

import java.sql.*;

public final class DatabaseHelper {

    private static final String DATABASE_NAME = "Bank";

    private static final String TABLE_CLIENTS = "clients";
    private static final String COLUMN_CLIENT_ID = "client_id";
    private static final String COLUMN_CLIENT_FIRSTNAME = "first_name";
    private static final String COLUMN_CLIENT_LASTNAME = "last_name";

    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String COLUMN_ACCOUNT_CLIENT_ID = "client_id";
    private static final String COLUMN_ACCOUNT_BALANCE = "balance";
    private static final String COLUMN_ACCOUNT_ID = "account_id";

    private static final String TABLE_TRANSACTIONS = "transactions";
    private static final String COLUMN_TRANSACTION_ID = "transaction_id";
    private static final String COLUMN_TRANSACTION_TYPE = "transaction_type";
    private static final String COLUMN_TRANSACTION_DATE = "transaction_date";
    private static final String COLUMN_TRANSACTION_AMOUNT = "transaction_amount";
    private static final String COLUMN_TRANSACTIONS_CLIENT_ID = "client_id";

    private static final String DB_URI = DataResources.getDBUri();
    private static final String NEW_DB_URI = DataResources.getNewDbUri();
    private static final String DB_USER = DataResources.getDBUsername();
    private static final String DB_PW = DataResources.getDBPassword();
    private static final String TABLE_PW = "pins";
    private static final String COLUMN_SALT_VAL = "salt";
    private static final String COLUMN_PIN = "pin";

    /**
     *  Creates database on start if it doesn't already exist
     */
    public static void createDatabase() {
        final String CREATE_CLIENT_DB            = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME + ";";

        try (Connection connection = DriverManager.getConnection(NEW_DB_URI, DB_USER, DB_PW)) {
            try (PreparedStatement createDB = connection.prepareStatement(CREATE_CLIENT_DB)) {

                createDB.execute();

                DatabaseMetaData metaData = connection.getMetaData();
                System.out.printf("The driver name is: %s \n", metaData.getDriverName());
                System.out.println("A new database has been successfully created ");
            }
        } catch (SQLException ex) {
            System.err.println("Database exists, nothing was changed");
            System.err.println("Message: " + ex.getMessage());
            System.err.println("Cause: " + ex.getCause());
        }
        createDBTables();
    }

    private static void createDBTables() {
        final String CREATE_CLIENT_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CLIENTS + "("
                + COLUMN_CLIENT_ID + " BIGINT AUTO_INCREMENT NOT NULL,"
                + COLUMN_CLIENT_FIRSTNAME + " VARCHAR(30) NOT NULL,"
                + COLUMN_CLIENT_LASTNAME + " VARCHAR(40) NOT NULL,"
                + "PRIMARY KEY clientId_pk (" + COLUMN_CLIENT_ID + "));";

        final String CREATE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_ACCOUNTS + "("
                + COLUMN_ACCOUNT_CLIENT_ID + " BIGINT NOT NULL,"
                + COLUMN_ACCOUNT_BALANCE + " DECIMAL(13,2) DEFAULT 0.0,"
                + COLUMN_ACCOUNT_ID + " INT AUTO_INCREMENT NOT NULL,"
                + "PRIMARY KEY account_id_pk (" + COLUMN_ACCOUNT_ID + "),"
                + "FOREIGN KEY client_id_fk (" + COLUMN_ACCOUNT_CLIENT_ID + ") REFERENCES " + TABLE_CLIENTS + " ("
                + COLUMN_CLIENT_ID + "));";

        final String CREATE_TRANSACTION_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_TRANSACTIONS + "("
                + COLUMN_TRANSACTION_ID + " INT AUTO_INCREMENT NOT NULL,"
                + COLUMN_TRANSACTION_TYPE + " VARCHAR(10) NOT NULL,"
                + COLUMN_TRANSACTION_DATE + " DATETIME NOT NULL,"
                + COLUMN_TRANSACTION_AMOUNT + " DECIMAL(13,2) DEFAULT 0.0,"
                + COLUMN_TRANSACTIONS_CLIENT_ID + " BIGINT NOT NULL,"
                + "PRIMARY KEY transactionId_pk (" + COLUMN_TRANSACTION_ID + "),"
                + "FOREIGN KEY clientId_fk (" + COLUMN_TRANSACTIONS_CLIENT_ID + ") REFERENCES " + TABLE_CLIENTS + " ("
                + COLUMN_TRANSACTIONS_CLIENT_ID + "));";

        final String CREATE_PW_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PW + "("
                + COLUMN_CLIENT_ID + " BIGINT NOT NULL,"
                + COLUMN_SALT_VAL + " VARCHAR(31) NOT NULL,"
                + COLUMN_PIN + " TEXT NOT NULL,"
                + "PRIMARY KEY salt_pk (" + COLUMN_SALT_VAL + "),"
                + "FOREIGN KEY clientId_fk (" + COLUMN_CLIENT_ID + ") REFERENCES " + TABLE_CLIENTS + " ("
                + COLUMN_CLIENT_ID + "));";

        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER, DB_PW)) {
            try (PreparedStatement createClientStmt = connection.prepareStatement(CREATE_CLIENT_TABLE);
                PreparedStatement createAccountStmt = connection.prepareStatement(CREATE_ACCOUNT_TABLE);
                PreparedStatement createTransactionStmt = connection.prepareStatement(CREATE_TRANSACTION_TABLE);
                PreparedStatement createEncryptStmt = connection.prepareStatement(CREATE_PW_TABLE)) {

                connection.setAutoCommit(false);
                createClientStmt.execute();
                createAccountStmt.execute();
                createTransactionStmt.execute();
                createEncryptStmt.execute();

            }
            connection.commit();
        } catch (SQLException ex) {
            System.err.println("Table already exists, nothing was changed");
            ex.printStackTrace();
        }
    }
}
