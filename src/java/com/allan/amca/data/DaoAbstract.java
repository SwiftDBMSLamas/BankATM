package com.allan.amca.data;

import java.sql.SQLException;

public abstract class DaoAbstract<T, N> implements Dao<T, N> {

    @Override
    public final boolean update(T client, N id) throws SQLException {
        if (client == null || id == null) {
            throw new IllegalArgumentException("Neither arguments can be null");
        }
        return executeUpdate(client, id);
    }

    @Override
    public final boolean delete( T toDelete) {
        if (toDelete == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        return deleteRecord(toDelete);
    }

    @Override
    public final boolean create(T toCreate) throws SQLException {
        if (toCreate == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        return addRecord(toCreate);
    }

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
