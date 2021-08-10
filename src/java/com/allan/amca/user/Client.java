package com.allan.amca.user;

import java.util.HashMap;

/**
 * Client class
 * @author allanaranzaso
 */
public class Client extends User {

    private long clientCardID;
    private static HashMap<Integer, Client> clientMap;

    static
    {
        clientMap = new HashMap<>();
    }

    /**
     * Constructor that will register a new Client object and add them to the database
     * @param firstName First name of the client
     * @param lastName Last name of the client
     * @param password Password that the client has chosen
     */
    public Client(final String firstName, final String lastName, final String password) {
        super(firstName, lastName, password);
    }

    /**
     * Get the client's ID
     * @return client's ID
     */
    public long getClientID() {
        return clientCardID;
    }

    /**
     * Set the client's ID if needed
     * @param clientID client's ID to set
     */
    public void setClientID(final long clientID) {
        clientCardID = clientID;
    }

    /**
     * Pass client from one class to another by adding client object to the Map
     * @param request the type of request to put into the key value. For purposes of this project, request is 1.
     * @param clientObj the client object to send
     * @return the client object you are sending. Actual return may not be needed.
     */
    public static Client sendClient(final int request, final Client clientObj) {
        if (clientMap == null) {
            clientMap = new HashMap<>();
        }
        clientMap.put(request, clientObj);
        return clientObj;
    }

    /**
     * Retrieves the client from the Map using the request key
     * @param request the request to retrieve from the Map. Is the key. For purposes of this
     *                project, request to pass is 1.
     * @return the client value retrieved from the Map
     */
    public static Client getClient(final int request) {
        return clientMap.get(request);
    }

    /**
     * Clear the map when application is finished
     */
    public static void dispose() {
        clientMap.clear();
    }
}
