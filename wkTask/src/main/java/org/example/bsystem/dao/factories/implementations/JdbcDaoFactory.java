package org.example.bsystem.dao.factories.implementations;

import org.example.bsystem.dao.daos.jdbc.JdbcClientDao;
import org.example.bsystem.dao.factories.interfaces.DaoFactory;
import org.example.bsystem.dao.interfaces.ClientDao;

/**
 * creates jdbc dao implementations
 */
public class JdbcDaoFactory implements DaoFactory {
    /**
     * gets a jdbc client dao implementation
     * @return returns a jdbc client dao instance
     */
    @Override
    public ClientDao getClientDao() {
        return new JdbcClientDao();
    }
}
