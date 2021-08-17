package com.allan.amca.register;

import com.allan.amca.data.Dao;
import com.allan.amca.data.DaoFactory;
import com.allan.amca.enums.DaoType;
import com.allan.amca.user.Client;
import com.allan.amca.user.UserDaoImpl;
import com.allan.amca.user.UserFactory;

import java.sql.SQLException;

public class Register {

    private static final Register instance = new Register();
    private static final int NEW_CLIENT_SEND_REQ = 1;

    private Register() {}

    public boolean register(final String firstName, final String lastName, final String pin) throws SQLException {
        Dao<Client, Long> dao = DaoFactory.createDao(DaoType.USER);
        boolean registerSuccess;
        final Client newClient;

        registerSuccess = dao.create(UserFactory.createUser(firstName, lastName, pin));
        if (registerSuccess) {
            newClient = dao.retrieve(UserDaoImpl.getNewClientIDFromDB());
            openAccount(newClient);
            Client.sendClient(NEW_CLIENT_SEND_REQ, newClient);
        }
        return registerSuccess;
    }

    private void openAccount(final Client clientToAdd) {
        Dao<Client, Long> accountDao = DaoFactory.createDao(DaoType.ACCOUNT);
        try {
            accountDao.create(clientToAdd);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Register newInstance() {
        return instance;
    }
}
