package com.allan.amca.data;

/**
 * Base class, which implements the dao interface. I've tried to generify the class in order to return different
 * types of dao implementations.
 * @param <T> the type of object to create/return. Usually a Client or Transaction object
 * @param <N> the type of number to use. Usually Long to support the client ID
 */
public abstract class DaoAbstract<T, N> implements Dao<T, N> {

    /**
     * Perform an update operation dependent on the subclass that was instantiated during runtime.
     * @param client either the client or transaction object to update
     * @param id the client or transaction id to search up
     * @return true if the query executed successfully. Otherwise, false
     * referential/entity integrity
     */
    @Override
    public final boolean update(T client, N id) {
        if (client == null || id == null) {
            throw new IllegalArgumentException("Neither arguments can be null");
        }
        return executeUpdate(client, id);
    }

    /**
     * Perform a delete operation dependent on the subclass that was instantiated during runtime.
     * @param toDelete the object to delete. Usually a Client or Transaction
     * @return true if the query executed successfully. Otherwise, false
     * referential/entity integrity
     */
    @Override
    public final boolean delete(T toDelete) {
        if (toDelete == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        return deleteRecord(toDelete);
    }

    /**
     * Perform an add (create) operation dependent on the subclass
     * @param toCreate the object to add (create). Usually a Client or Transaction
     * @return true if the query executed successfully. Otherwise, false
     */
    @Override
    public final boolean create(T toCreate) {
        if (toCreate == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        return addRecord(toCreate);
    }

    /**
     * Perform a read (retrieval) operation dependent on the subclass
     * @param toRetrieve the object to retrieve from the database. Usually a Client ID or Transaction ID
     * @return the data from the database.
     */
    @Override
    public final <T> T retrieve(N toRetrieve) {
        if (toRetrieve == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        return readRecord(toRetrieve);
    }

    protected abstract <T> T readRecord(N toRetrieve);
    protected abstract boolean addRecord(T toCreate);
    protected abstract boolean deleteRecord(T toDelete);
    protected abstract boolean executeUpdate(T client, N id);
}
