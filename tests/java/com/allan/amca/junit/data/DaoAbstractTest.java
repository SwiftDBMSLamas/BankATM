package com.allan.amca.junit.data;

import com.allan.amca.data.Dao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class DaoAbstractTest {

    protected <T> void update(final Dao<T, Number>  dao,
                              final Number          number,
                              final T               value,
                              final boolean         expectedResult) throws SQLException {
        assertThat(dao.update(value, number), equalTo(expectedResult));
    }

    protected <T> void delete(final Dao<T, Number>  dao,
                              final T               value,
                              final boolean         expectedResult) throws SQLException {
        assertThat(dao.delete(value), equalTo(expectedResult));
    }

    protected <T> void create(final Dao<T, Number>  dao,
                              final T               value,
                              final boolean         expectedResult) throws SQLException {
        assertThat(dao.create(value), equalTo(expectedResult));
    }

    protected <T> void retrieve(final Dao<T, Number>    dao,
                                final Number            number,
                                final T                 expectedResult) throws SQLException {
        assertThat(dao.retrieve(number), equalTo(expectedResult));
    }

    protected void badRead(final Executable                     executable,
                           final Class<? extends Exception>     expectedException,
                           final String                         expectedMessage) {
        final Throwable ex;

        ex = assertThrows(expectedException, executable);
        assertThat(ex.getMessage(), equalTo(expectedMessage));
    }
}