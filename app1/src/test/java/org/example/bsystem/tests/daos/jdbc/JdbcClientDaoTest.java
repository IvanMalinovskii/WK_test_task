package org.example.bsystem.tests.daos.jdbc;

import org.example.bsystem.dao.daos.jdbc.JdbcClientDao;
import org.example.bsystem.dao.entities.Client;
import org.example.bsystem.dao.interfaces.ClientDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * a class for testing jdbc clientDao implementation
 */
public class JdbcClientDaoTest {

    /**
     * tests getAllEntities method
     */
    @Test
    public void testGetAllEntities() {
        ClientDao dao = new JdbcClientDao();
        List<Client> clients = dao.getAllEntities();
        Assert.assertNotNull("clients shouldn't be null", clients);
    }
}
