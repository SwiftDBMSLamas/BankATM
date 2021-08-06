package com.allan.amca.data;

import com.allan.amca.enums.DaoType;

public class DaoFactoryGenerator implements DaoFactory {
    @Override
    public DaoFactory createDao(DaoType type) {
        final DaoFactory factory;

        switch (type) {
//            case USER -> factory = new UserDaoImpl();
        }
        return null;
    }

    public static DaoFactory createFactory() {
        final DaoFactory factory;
        factory = new DaoFactoryGenerator();

        return factory;
    }
}
