package org.exaple.coucher.services;

import org.exaple.coucher.logic.couchbase.daos.ClientsDao;
import org.exaple.coucher.logic.couchbase.entities.IdBalancePair;
import org.exaple.coucher.logic.couchbase.managers.PropertyManager;
import org.exaple.coucher.logic.interfaces.AMQPSender;

import java.util.List;

/**
 * gets data from a cache base and sends it into a broker
 */
public class AMQPService implements Service {
    private final ClientsDao clientsDao;
    private final AMQPSender sender;
    private final double minBalance;

    /**
     * initializes fields with input values
     * @param clientsDao a doa to get data from a cache base
     * @param sender an AMQP sender to send data into a broker
     */
    public AMQPService(ClientsDao clientsDao, AMQPSender sender) {
        PropertyManager properties = PropertyManager.getManager();
        this.sender = sender;
        this.clientsDao = clientsDao;
        minBalance = Double.parseDouble(properties.getProperty("query.minBalance"));
    }

    /**
     * gets data and sends in into a broker
     */
    @Override
    public void doService() {
        List<IdBalancePair> clients = clientsDao.getClientsByBalance(minBalance);
        sender.send(clients);
    }
}
