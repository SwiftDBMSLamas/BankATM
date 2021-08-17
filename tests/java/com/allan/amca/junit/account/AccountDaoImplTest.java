package com.allan.amca.junit.account;

import com.allan.amca.data.Dao;
import com.allan.amca.data.DaoFactory;
import com.allan.amca.enums.DaoType;
import com.allan.amca.junit.data.DaoAbstractTest;
import com.allan.amca.user.Client;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

class AccountDaoImplTest extends DaoAbstractTest {

    @Test
    void readRecord() throws SQLException {
        Dao daoImpl = DaoFactory.createDao(DaoType.ACCOUNT);

        retrieve(daoImpl, 4519011123012370L, BigDecimal.valueOf(1243.51));
        retrieve(daoImpl, 4519011123012372L, BigDecimal.valueOf(37000.00));
        retrieve(daoImpl, 4519011123012380L, BigDecimal.valueOf(2000.0));
    }

    @Test
    void badRead() {
        Dao<Client, Long> daoImpl = DaoFactory.createDao(DaoType.ACCOUNT);

        badRead(() -> daoImpl.retrieve(null), IllegalArgumentException.class, "Argument cannot be null");
    }

//    @Test
    void addRecord() throws SQLException {
        Dao daoImpl                     = DaoFactory.createDao(DaoType.ACCOUNT);
        Dao<Client, Long> retrieveUser  = DaoFactory.createDao(DaoType.USER);
        Client clientToAdd              = retrieveUser.retrieve(4519011123012445L);

        if (clientToAdd != null) {
            /**
             * if user exists during the test, delete the account and proceed to adding it
             * this is so that you don't run into any false positives while running the unit tests
             */
            // failing foreign key constraint on the clients and transactions tables because deleting client ID
//            daoImpl.delete(clientToAdd);
        }
        create(daoImpl, clientToAdd, true);
    }

//    @Test
    void deleteRecord() throws SQLException {
        Dao daoImpl                         = DaoFactory.createDao(DaoType.ACCOUNT);
        Dao<Client, Long> retrieveUser      = DaoFactory.createDao(DaoType.USER);
        Client clientToDelete               = retrieveUser.retrieve(4519011123012445L);

        if (clientToDelete != null) {
            /**
             * if user exists during the test, perform the test. The test will recreate the object afterwards
             * This is to prevent any false positives while running the unit tests
             */
            delete(daoImpl, clientToDelete, true);
        }
        daoImpl.create(clientToDelete);
    }

    @Test
    void executeUpdate() throws SQLException {
        Dao daoImpl                     = DaoFactory.createDao(DaoType.ACCOUNT);
        Dao<Client, Long> retrieveUser  = DaoFactory.createDao(DaoType.USER);
        Client clientToUpdate           = retrieveUser.retrieve(4519011123012380L);

        update(daoImpl, 2000L , clientToUpdate, true);
    }
}