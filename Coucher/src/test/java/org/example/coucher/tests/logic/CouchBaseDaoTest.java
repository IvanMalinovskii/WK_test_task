package org.example.coucher.tests.logic;

import org.exaple.coucher.logic.couchbase.CouchBaseDao;
import org.exaple.coucher.logic.couchbase.entities.IdBalancePair;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CouchBaseDaoTest {
    private static CouchBaseDao couchBaseDao;
    private static List<IdBalancePair> clients;

    @BeforeAll
    static void initialize() {
        couchBaseDao = new CouchBaseDao();
        clients = new ArrayList<>();
        clients.add(new IdBalancePair(-1, 211424.3));
        clients.add(new IdBalancePair(-2, 2114.3));
        clients.add(new IdBalancePair(-3, 424.3));
        clients.add(new IdBalancePair(-4, 424.3));
    }

    @Test
    @Order(1)
    public void testUpsertClients() throws InterruptedException {
        couchBaseDao.upsertClients(clients);
        Thread.sleep(1000);
        List<IdBalancePair> baseClients = couchBaseDao.getAll();

        for (IdBalancePair pair : clients) {
            Assert.assertTrue("the instance " + pair + " should be in the list",
                    baseClients.contains(pair));
        }
    }

    @Test
    @Order(2)
    public void testGetClientsCacheByBalance() {
        List<IdBalancePair> gottenClients = couchBaseDao.getClientsCacheByBalance(100);
        Assert.assertTrue("count of elements should be more than 3", gottenClients.size() >= 4);
        couchBaseDao.deleteAllLowerThanZero();
    }
}
