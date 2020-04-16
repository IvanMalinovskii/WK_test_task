package org.example.bsystem.dao.factories.interfaces;

import org.example.bsystem.dao.interfaces.ClientDao;

/**
 * describes getting daos logic
 */
public interface DaoFactory {
    /**
     * gets a client dao implementation
     * @return return a client dao instance
     */
    ClientDao getClientDao();
}
