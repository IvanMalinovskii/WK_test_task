package org.exaple.coucher.logic.couchbase;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;

import java.util.ArrayList;
import java.util.List;

/**
 * gets/sets data from/to the base
 */
public class CouchBaseDao {
    private JsonConverter converter;
    private CouchBaseConnector connector;

    public CouchBaseDao() {
        connector = CouchBaseConnector.getConnector();
        converter = new JsonConverter();
    }

    public void upsertClients(List<IdBalancePair> clients) {
        Bucket bucket = connector.getBucket();
        clients.forEach(client -> bucket
                .upsert(converter.getDocument(client)));
    }

    public List<IdBalancePair> getClientsCacheByBalance(double minBalance) {
        Bucket bucket = connector.getBucket();
        N1qlQueryResult result = bucket.query(
                N1qlQuery.parameterized("select buck.* from buck where balance >= $balance",
                        JsonObject.create().put("balance", minBalance)));
        List<IdBalancePair> clients = new ArrayList<>();
        result.allRows().forEach(row -> clients.add(converter.getPair(row.value())));

        return clients;
    }

    public List<IdBalancePair> getAll() {
        List<IdBalancePair> result = new ArrayList<>();
        Bucket bucket = connector.getBucket();
        bucket.query(N1qlQuery.simple("select buck.* from buck"))
        .allRows().forEach(row -> result.add(converter.getPair(row.value())));
        return result;
    }

    public void deleteAllLowerThanZero() {
        Bucket bucket = connector.getBucket();
        bucket.query(N1qlQuery.simple("delete from buck where client_id < 0"));
    }
}
