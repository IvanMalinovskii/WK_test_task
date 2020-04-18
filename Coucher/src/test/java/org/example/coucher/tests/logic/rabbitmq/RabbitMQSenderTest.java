package org.example.coucher.tests.logic.rabbitmq;

import org.exaple.coucher.logic.couchbase.entities.IdBalancePair;
import org.exaple.coucher.logic.rabbitmq.RabbitMQSender;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class RabbitMQSenderTest {
    private static final ByteArrayOutputStream OUT = new ByteArrayOutputStream();
    private static final PrintStream ORIGINAL = System.out;

    @BeforeAll
    static void setUpStream() {
        System.setOut(new PrintStream(OUT));
    }

    @AfterAll
    static void returnStream() {
        System.setOut(ORIGINAL);
    }

    @Test
    public void testSend() {
        RabbitMQSender sender = new RabbitMQSender();
        List<IdBalancePair> clients = new ArrayList<>();
        clients.add(new IdBalancePair(1, 21456));

        sender.send(clients);

        String expected = "1/21456.0";
        String[] output = OUT.toString().split(" ");
        String actual = output[output.length - 1].trim();
        Assert.assertEquals("string should be: " + expected, expected, actual);
    }
}
