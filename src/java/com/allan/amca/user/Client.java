package com.allan.amca.user;

public class Client extends User {

    private static int clientID = 123012300;
    private String clientCardID;
    private static final String CLIENT_CARD_PREFIX;

    static {
        CLIENT_CARD_PREFIX = "4519011";
    }

    public Client(final String firstName, final String lastName, final String password) {
        super(firstName, lastName, password);
        createClientID();
    }

    public void createClientID() {
        final String clientCard;
        clientID++;
        clientCard = CLIENT_CARD_PREFIX + clientID;
        this.clientCardID = clientCard;
    }

    public String getClientID() {
        return clientCardID;
    }
}
