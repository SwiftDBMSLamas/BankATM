package com.allan.amca.data;

import com.allan.amca.user.Client;

import java.sql.SQLException;

public interface UserDao {

    void onCreate();
    boolean addClient(Client toAdd) throws SQLException;
    Client getClient(long idToRetrieve) throws SQLException;
    boolean updateClient(Client toUpdate) throws SQLException;
    boolean deleteClient(long toDelete) throws SQLException;
}
