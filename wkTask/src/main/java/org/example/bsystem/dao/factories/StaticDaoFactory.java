package org.example.bsystem.dao.factories;

import org.example.bsystem.dao.factories.implementations.JdbcDaoFactory;
import org.example.bsystem.dao.factories.interfaces.DaoFactory;

/**
 * generates particular dao factories implementations
 */
public class StaticDaoFactory {
    private StaticDaoFactory() {}
    /**
     * gets a dao factory implementation according to the input type
     * @param type dao implementation type
     * @return returns a dao factory implementation
     */
    public static DaoFactory getDaoFactory(String type) {
        if (type.equals("JDBC"))
            return new JdbcDaoFactory();
        else
            throw new RuntimeException("invalid dao factory type");
    }
}
