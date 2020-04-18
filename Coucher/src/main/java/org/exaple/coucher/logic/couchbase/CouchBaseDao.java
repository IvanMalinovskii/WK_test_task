package org.exaple.coucher.logic.couchbase;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import org.exaple.coucher.logic.couchbase.entities.IdBalancePair;
import org.exaple.coucher.logic.couchbase.managers.CouchBaseConnector;
import org.exaple.coucher.logic.couchbase.managers.PropertyManager;

import java.util.ArrayList;
import java.util.List;

/**
 * gets/sets data from/to the base
 */
public class CouchBaseDao {
    private final PropertyManager properties;
    private JsonConverter converter;
    private CouchBaseConnector connector;

    public CouchBaseDao() {
        properties = PropertyManager.getManager();
        connector = CouchBaseConnector.getConnector();
        converter = new JsonConverter();
    }

    /**
     * insert of update documents in the base
     * @param clients collection to be upserted
     */
    public void upsertClients(List<IdBalancePair> clients) {
        Bucket bucket = connector.getBucket();
        clients.forEach(client -> bucket
                .upsert(converter.getDocument(client)));
    }

    /**
     * gets clients which balance is more than an input value
     * @param minBalance min balance value
     * @return returns a list of result pairs
     */
    public List<IdBalancePair> getClientsCacheByBalance(double minBalance) {
        Bucket bucket = connector.getBucket();
        N1qlQueryResult result = bucket.query(
                N1qlQuery.parameterized(properties.getProperty("query.selectByBalance"),
                        JsonObject.create().put("balance", minBalance)));
        List<IdBalancePair> clients = new ArrayList<>();
        result.allRows().forEach(row -> clients.add(converter.getPair(row.value())));

        return clients;
    }

    /**
     * gets all the clients form the base
     * @return returns a list of result values
     */
    public List<IdBalancePair> getAll() {
        List<IdBalancePair> result = new ArrayList<>();
        Bucket bucket = connector.getBucket();
        bucket.query(N1qlQuery.simple(properties.getProperty("query.selectAll")))
        .allRows().forEach(row -> result.add(converter.getPair(row.value())));
        return result;
    }

    /**
     * deletes clients which ids are less than zero
     */
    public void deleteAllLowerThanZero() {
        Bucket bucket = connector.getBucket();
        bucket.query(N1qlQuery.simple(properties.getProperty("query.deleteLessTanZero")));
    }
}
