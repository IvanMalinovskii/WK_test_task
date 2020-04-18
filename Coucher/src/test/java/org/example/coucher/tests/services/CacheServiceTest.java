package org.example.coucher.tests.services;

import org.exaple.coucher.logic.GetHttpRequest;
import org.exaple.coucher.logic.XSLTTransformer;
import org.exaple.coucher.logic.couchbase.daos.ClientsDao;
import org.exaple.coucher.logic.couchbase.daos.CouchBaseClientsDao;
import org.exaple.coucher.logic.couchbase.entities.IdBalancePair;
import org.exaple.coucher.logic.interfaces.Request;
import org.exaple.coucher.logic.interfaces.Transformer;
import org.exaple.coucher.services.CacheService;
import org.exaple.coucher.services.Service;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CacheServiceTest {
    @Test
    public void testDoService() {
        Request request = Mockito.mock(GetHttpRequest.class);
        Transformer transformer = Mockito.mock(XSLTTransformer.class);
        ClientsDao clientsDao = Mockito.mock(CouchBaseClientsDao.class);

        Service service = new CacheService(request, transformer, clientsDao);
        Mockito.when(transformer.getTransformed(Mockito.anyString())).thenReturn("\n1/12443");
        service.doService();

        Mockito.verify(request).doRequest("text/xml");
        Mockito.verify(transformer).getTransformed(Mockito.anyString());
        Mockito.verify(clientsDao).upsertClients(Mockito.anyListOf(IdBalancePair.class));
    }
}
