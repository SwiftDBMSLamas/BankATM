package Data;

import com.allan.amca.user.Client;

public interface Helper {

    void onCreate();
    void addClient(Client toAdd);
    void updateClient(Client toUpdate);
    void deleteClient(Client toDelete);
}
