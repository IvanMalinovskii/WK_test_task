package org.exaple.coucher.services;

import org.exaple.coucher.logic.GetHttpRequest;
import org.exaple.coucher.logic.XSLTTransformer;
import org.exaple.coucher.logic.couchbase.CouchBaseDao;
import org.exaple.coucher.logic.couchbase.entities.IdBalancePair;
import org.exaple.coucher.logic.couchbase.managers.PropertyManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * does HttpRequest and put results into a couchbase
 */
public class CacheService {
    private final GetHttpRequest httpRequest;
    private final XSLTTransformer transformer;
    private final CouchBaseDao couchBaseDao;

    public CacheService() {
        final PropertyManager properties = PropertyManager.getManager();
        httpRequest = new GetHttpRequest(properties.getProperty("http.url"));
        String path = getClass().getClassLoader().getResource(properties.getProperty("xsl.name")).getPath();
        transformer = new XSLTTransformer(path);
        couchBaseDao = new CouchBaseDao();
    }

    public void cacheData() {
        String result = httpRequest.doRequest("text/xml");
        String pairs = transformer.getTransformed(result);
        List<IdBalancePair> clients = convertIntoPair(pairs);
        couchBaseDao.upsertClients(clients);
    }

    private List<IdBalancePair> convertIntoPair(String pairs) {
        Scanner scanner = new Scanner(pairs);
        scanner.nextLine();
        List<IdBalancePair> clients = new ArrayList<>();
        while (scanner.hasNext()) {
            String[] values = scanner.nextLine().split("[/]");
            clients.add(new IdBalancePair(Integer.parseInt(values[0]), Double.parseDouble(values[1])));
        }
        return clients;
    }
}
