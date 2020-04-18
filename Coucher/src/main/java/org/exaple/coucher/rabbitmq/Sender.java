package org.exaple.coucher.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.exaple.coucher.logic.couchbase.entities.IdBalancePair;
import org.exaple.coucher.logic.couchbase.managers.PropertyManager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * sends data to a broker
 */
public class Sender {
    private static final Logger LOGGER = LogManager.getLogger(Sender.class.getName());
    private static final String QUEUE_NAME = "clients_cache";
    private final ConnectionFactory factory;

    /**
     * initializes a connection factory
     */
    public Sender() {
        final PropertyManager properties = PropertyManager.getManager();
        factory = new ConnectionFactory();
        factory.setHost(properties.getProperty("rmq.host"));
    }

    /**
     * send pair values into a broker
     * @param clients data to send
     */
    public void send(List<IdBalancePair> clients) {
        try(Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            for (IdBalancePair pair : clients) {
                try {
                    channel.basicPublish("", QUEUE_NAME, null, pair.toString().getBytes());
                    LOGGER.info("pair has been sent: " + pair.toString());
                }
                catch (IOException e) {
                    LOGGER.error(LOGGER.getName() + " - can't send a message: " + e);
                }
            }
        }
        catch (TimeoutException | IOException e) {
            LOGGER.error(LOGGER.getName() + " - cant't open connection: " + e);
        }
    }
}
