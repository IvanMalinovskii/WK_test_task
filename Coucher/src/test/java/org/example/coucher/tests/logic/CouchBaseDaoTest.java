package org.example.coucher.tests.logic;

import org.exaple.coucher.logic.couchbase.CouchBaseDao;
import org.exaple.coucher.logic.couchbase.IdBalancePair;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CouchBaseDaoTest {
    @Test
    public void testUpsertClients() {
        CouchBaseDao couchBaseDao = new CouchBaseDao();
        ArrayList<IdBalancePair> clients = new ArrayList<>();
        clients.add(new IdBalancePair(-1, 211424.3));
        clients.add(new IdBalancePair(-2, 2114.3));
        clients.add(new IdBalancePair(-3, 424.3));
        clients.add(new IdBalancePair(-4, 424.3));
        couchBaseDao.upsertClients(clients);

        List<IdBalancePair> baseClients = couchBaseDao.getAll();

        for (IdBalancePair pair : clients) {
            Assert.assertTrue("the instance " + pair + " should be in the list",
                    baseClients.contains(pair));
        }
    }

    @Test
    public void testGetClientsCacheByBalance() {
        CouchBaseDao couchBaseDao = new CouchBaseDao();
        List<IdBalancePair> clients = couchBaseDao.getClientsCacheByBalance(100);
        Assert.assertTrue("count of elements should be more than 4", clients.size() >= 4);
        System.out.println(clients);
        couchBaseDao.deleteAllLowerThanZero();
    }
}
