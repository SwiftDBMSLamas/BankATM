package main.com.allan.amca.data;

import main.com.allan.amca.account.AccountDaoImpl;
import main.com.allan.amca.enums.DaoType;
import main.com.allan.amca.transaction.TransactionDaoImpl;
import main.com.allan.amca.user.UserDaoImpl;

/**
 * DAO factory that will instantiate the appropriate Dao object to use depending on the type.
 * Has one static method createDao(), which will return the Dao object.
 * @version 1.0
 */
public class DaoFactory {
    /**
     * Creates the Dao object to use.
     * @param type the type of dao to create. Uses enums to determine.
     * @return the dao implementation
     * @throws IllegalStateException if the DAO being created does not exist.
     */
    public static <T, N> Dao<T, N> createDao(final DaoType type) {
        final Dao factory;

        switch (type) {
            case USER -> factory = new UserDaoImpl<T, N>();
            case ACCOUNT -> factory = new AccountDaoImpl<T, N>();
            case TRANSACTION -> factory = new TransactionDaoImpl<T, N>();
            default -> throw new IllegalStateException("DAO does not exist");
        }
        return factory;
    }
}
