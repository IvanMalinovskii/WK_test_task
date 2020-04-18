package org.exaple.coucher.logic.interfaces;

import org.exaple.coucher.logic.couchbase.entities.IdBalancePair;

import java.util.List;

/**
 * sends data via AMQP protocol
 */
public interface AMQPSender {
    /**
     * sends clients into a broker
     * @param clients a clients list to send
     */
    void send(List<IdBalancePair> clients);
}
