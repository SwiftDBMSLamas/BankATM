package com.allan.amca.data;

import java.sql.SQLException;
// TODO: next iteration, create REST API to communicate between database and the program logic
/**
 * Interface to override and for providing flexibility
 * @param <T> the type of object to create a Generic type for.
 *           In the case of this project, will either be a Client or Transaction object
 * @param <N> the type of number to create a Generic type for.
 *           In the case of this project, will typically use the Long wrapper class
 * @author allanaranzaso
 * @version 1.0
 */
public interface Dao<T, N> {

    boolean create(T toCreate) throws SQLException;
    boolean update(T toUpdate, N id) throws SQLException;
    boolean delete(T toDelete) throws SQLException;
    default T retrieve(){return null;};
    default <T> T retrieve(N toRetrieve){return null;};


}
