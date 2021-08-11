package com.allan.amca.junit.account;

import com.allan.amca.data.Dao;
import com.allan.amca.data.DaoFactoryGenerator;
import com.allan.amca.enums.DaoType;
import com.allan.amca.junit.data.DaoAbstractTest;
import com.allan.amca.user.Client;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class AccountDaoImplTest extends DaoAbstractTest {

    @Test
    void readRecord() throws SQLException {
        Dao daoImpl = DaoFactoryGenerator
                        .createFactory()
                        .createDao(DaoType.ACCOUNT);

        retrieve(daoImpl, 4519011123012370L, 1243.51);
        retrieve(daoImpl, 4519011123012372L, 25000.00);
        retrieve(daoImpl, 4519011123012380L, 121212.0);
    }

    @Test
    void badRead() {
        Dao daoImpl = DaoFactoryGenerator
                .createFactory()
                .createDao(DaoType.ACCOUNT);

        badRead(() -> daoImpl.retrieve(null), IllegalArgumentException.class, "Argument cannot be null");
    }

    @Test
    void addRecord() throws SQLException {
        Dao daoImpl = DaoFactoryGenerator
                        .createFactory()
                        .createDao(DaoType.ACCOUNT);
        Dao retrieveUser = DaoFactoryGenerator
                            .createFactory()
                            .createDao(DaoType.USER);
        Client clientToAdd = (Client) retrieveUser.retrieve(4519011123012444L);

        create(daoImpl, clientToAdd, true);
    }

    @Test
    void deleteRecord() throws SQLException {
        Dao daoImpl = DaoFactoryGenerator
                        .createFactory()
                        .createDao(DaoType.ACCOUNT);
        Dao retrieveUser = DaoFactoryGenerator
                            .createFactory()
                            .createDao(DaoType.USER);
        Client clientToDelete = (Client) retrieveUser.retrieve(4519011123012444L);

        delete(daoImpl, clientToDelete, true);
    }

    @Test
    void executeUpdate() throws SQLException {
        Dao daoImpl = DaoFactoryGenerator.createFactory().createDao(DaoType.ACCOUNT);
        Dao retrieveUser = DaoFactoryGenerator.createFactory().createDao(DaoType.USER);
        Client clientToUpdate = (Client) retrieveUser.retrieve(4519011123012380L);

        update(daoImpl, 121212L , clientToUpdate, true);
    }
}