package com.allan.amca.user;

public class Client extends User {

    private static final String USE_CLIENT_DB = "USE Clients";
    private long clientCardID;

    public Client(final String firstName, final String lastName, final String password) {
        super(firstName, lastName, password);
    }

    public long getClientID() {
        return clientCardID;
    }

    public void setClientID(final long clientID) {
        clientCardID = clientID;
    }

}
