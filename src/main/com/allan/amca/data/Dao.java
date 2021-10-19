package main.com.allan.amca.data;

/**
 * Interface to override. The data access object pattern will require the implementation of the methods below in the
 * base class DaoAbstract. The base class will then use the template method to leave the implementation up to the subclasses
 * @param <T> the type of object to create a Generic type for.
 *           In the case of this project, will either be a Client or Transaction object
 * @param <N> the type of number to create a Generic type for.
 *           In the case of this project, will typically use the Long wrapper class
 * @author allanaranzaso
 * @version 1.0
 */
public interface Dao<T, N>{

    boolean create(T toCreate);
    boolean update(T toUpdate, N id);
    boolean delete(T toDelete);

    <T> T retrieve(N toRetrieve);


}
