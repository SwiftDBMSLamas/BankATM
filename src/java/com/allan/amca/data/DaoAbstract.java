package com.allan.amca.data;

import java.sql.SQLException;

public abstract class DaoAbstract<T, N> implements Dao<T, N> {

    @Override
    public final boolean update(T client, N id) throws SQLException {
        return executeUpdate(client, id);
    }

    @Override
    public final boolean delete(T toDelete) throws SQLException {
        return deleteRecord(toDelete);
    }

    @Override
    public final boolean create(T toCreate) throws SQLException {
        return addRecord(toCreate);
    }

    @Override
    public final <T> T retrieve(N toRetrieve) {
        return readRecord(toRetrieve);
    }

    protected abstract <T> T readRecord(N toRetrieve);
    protected abstract boolean addRecord(T toCreate);
    protected abstract boolean deleteRecord(T toDelete);
    protected abstract boolean executeUpdate(T client, N id);
}
