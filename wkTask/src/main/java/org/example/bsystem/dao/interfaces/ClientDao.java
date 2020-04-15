package org.example.bsystem.dao.interfaces;

import org.example.bsystem.dao.entities.Client;

import java.util.List;

/**
 * describes a client dao functionality
 */
public interface ClientDao {
    /**
     * gets all the entities from a data base
     * @return returns a list with entities
     */
    List<Client> getAllEntities();
}
