package org.exaple.coucher.logic.couchbase.daos;

import org.exaple.coucher.logic.couchbase.entities.IdBalancePair;

import java.util.List;

public interface ClientsDao {
    void upsertClients(List<IdBalancePair> clients);
    List<IdBalancePair> getClientsByBalance(double minBalance);
}
