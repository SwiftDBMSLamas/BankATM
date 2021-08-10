package com.allan.amca.data;

import com.allan.amca.account.AccountDaoImpl;
import com.allan.amca.enums.DaoType;
import com.allan.amca.transaction.TransactionDaoImpl;

public class DaoFactoryGenerator implements DaoFactory {

    @Override
    public final DaoAbstract createDao(final DaoType type) {
        final DaoAbstract factory;

        switch (type) {
            case USER -> factory = new UserDaoImpl();
            case ACCOUNT -> factory = new AccountDaoImpl();
            case TRANSACTION -> factory = new TransactionDaoImpl();
            default -> throw new IllegalStateException("DAO does not exist");
        }
        return factory;
    }

    public static DaoFactory createFactory() {
        final DaoFactory factory;
        factory = new DaoFactoryGenerator();
        return factory;
    }
}
