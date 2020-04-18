package org.example.coucher.tests.services;

import org.exaple.coucher.logic.couchbase.daos.ClientsDao;
import org.exaple.coucher.logic.couchbase.daos.CouchBaseClientsDao;
import org.exaple.coucher.logic.couchbase.entities.IdBalancePair;
import org.exaple.coucher.logic.couchbase.managers.PropertyManager;
import org.exaple.coucher.logic.interfaces.AMQPSender;
import org.exaple.coucher.logic.rabbitmq.RabbitMQSender;
import org.exaple.coucher.services.AMQPService;
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
}
