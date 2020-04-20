package org.exaple.coucher.services;

import org.exaple.coucher.logic.couchbase.daos.ClientsDao;
import org.exaple.coucher.logic.couchbase.entities.IdBalancePair;
import org.exaple.coucher.logic.interfaces.Request;
import org.exaple.coucher.logic.interfaces.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * does HttpRequest and put results into a cache base
 */
public class CacheService implements Service {
    private final Request request;
    private final Transformer transformer;
    private final ClientsDao clientsDao;

    /**
     * initializes fields with input values
     * @param request request to a service with initial data
     * @param transformer transformer to transform a request result
     * @param clientsDao a dao to store transformed data
     */
    public CacheService(Request request, Transformer transformer, ClientsDao clientsDao) {
        this.request = request;
        this.transformer = transformer;
        this.clientsDao = clientsDao;
    }

    /**
     * does request and put data into a cache base
     */
    public void doService() {
        String result = request.doRequest("text/xml");
        String pairs = transformer.getTransformed(result);
        List<IdBalancePair> clients = convertIntoPair(pairs);
        clientsDao.upsertClients(clients);
    }

    /**
     * converts pairs string into a list
     * @param pairs pairs string to convert
     * @return returns a list with client_id/balance pairs
     */
    private List<IdBalancePair> convertIntoPair(String pairs) {
        String[] values = pairs.split("\\s");
        List<IdBalancePair> clients = new ArrayList<>();
        for (String value : values) {
            String[] pair = value.split("[/]");
            clients.add(new IdBalancePair(Integer.parseInt(pair[0]), Double.parseDouble(pair[1])));
        }
        return clients;
    }
}
