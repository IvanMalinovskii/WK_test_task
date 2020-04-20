package org.example.bsystem.tests.logic.parsers;

import org.example.bsystem.dao.entities.Client;
import org.example.bsystem.dao.entities.Rate;
import org.example.bsystem.logic.parsers.JSONWriter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class JSONWriterTest {
    private static final String RESULT1 =
            "{\"clients\":["
                    + "{"
                    + "\"client\":"
                    + "{"
                        + "\"balance\":123.321,"
                        + "\"rate\":"
                        + "{"
                            + "\"rate_id\":1,"
                            + "\"rate_name\":\"rate\""
                        + "},"
                        + "\"surname\":\"surname\","
                        + "\"login\":\"log\","
                        + "\"client_id\":12,"
                        + "\"email\":\"email\""
                    + "}}]}";

    private static final String RESULT2 = "{\"clients\":[]}";

    @Test
    public void testGetJsonString() {
        JSONWriter writer = new JSONWriter();

        Client client = new Client(12, "log", "email", "surname", 123.321,
                new Rate(1, "rate"));
        final String actual = writer.getJsonString(new ArrayList<>(Collections.singletonList(client)));
        Assert.assertEquals("strings should be the same", RESULT1, actual);
    }

    @Test
    public void testGetJsonStringEmptyList() {
        JSONWriter writer = new JSONWriter();

        final String actual = writer.getJsonString(new ArrayList<>());
        Assert.assertEquals("strings should be the same", RESULT2, actual);
    }
}
