package com.allan.amca.data;

import com.allan.amca.enums.DaoType;

public interface DaoFactory {
    Dao createDao(DaoType type);
}
