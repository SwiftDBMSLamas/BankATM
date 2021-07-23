package com.allan.amca.data;

import com.allan.amca.user.Client;

import java.sql.SQLException;

public interface Helper {

    void onCreate();
    void addClient(Client toAdd) throws SQLException;
    Client retrieveClient(Long idToRetrieve) throws SQLException;
    void updateClient(Client toUpdate) throws SQLException;
    void deleteClient(Long toDelete) throws SQLException;
}
