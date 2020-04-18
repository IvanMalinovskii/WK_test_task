package org.example.coucher.tests.services;

import org.exaple.coucher.logic.GetHttpRequest;
import org.exaple.coucher.logic.XSLTTransformer;
import org.exaple.coucher.logic.couchbase.daos.ClientsDao;
import org.exaple.coucher.logic.couchbase.daos.CouchBaseClientsDao;
import org.exaple.coucher.logic.couchbase.entities.IdBalancePair;
import org.exaple.coucher.logic.couchbase.managers.PropertyManager;
import org.exaple.coucher.logic.interfaces.AMQPSender;
import org.exaple.coucher.logic.interfaces.Request;
import org.exaple.coucher.logic.interfaces.Transformer;
import org.exaple.coucher.logic.rabbitmq.RabbitMQSender;
import org.exaple.coucher.services.AMQPService;
import org.exaple.coucher.services.CacheService;
import org.exaple.coucher.services.Service;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AMQPServiceTest {

    @Test
    public void testDoService() {
        double minBalance = Double.parseDouble(PropertyManager.getManager().getProperty("query.minBalance"));
        ClientsDao dao = Mockito.mock(CouchBaseClientsDao.class);
        AMQPSender sender = Mockito.mock(RabbitMQSender.class);
        AMQPService service = new AMQPService(dao, sender);
        service.doService();

        Mockito.verify(dao).getClientsByBalance(minBalance);
        Mockito.verify(sender).send(Mockito.anyListOf(IdBalancePair.class));
    }

    @Test
    public void test() {
        ClientsDao dao = new CouchBaseClientsDao();
        Transformer transformer = new XSLTTransformer(getClass().getClassLoader().getResource("clients.xsl").getPath());
        Request request = new GetHttpRequest("http://localhost:8080/bsystem/clients");

        Service service = new CacheService(request, transformer, dao);

        service.doService();
    }
}
