package org.example.bsystem.tests.logic;

import org.example.bsystem.dao.entities.Client;
import org.example.bsystem.dao.entities.Rate;
import org.example.bsystem.logic.XMLWriter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * tests an XMLWriter class
 */
public class XMLWriterTest {

    private static final String RESULT1 =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"
            + "<clients xmlns=\"http://...\">"
                + "<client><client_id>12</client_id>"
                + "<login>log</login>"
                + "<email>email</email>"
                + "<surname>surname</surname>"
                + "<balance>123.321</balance>"
                + "<rate>"
                    + "<rate_id>1</rate_id>"
                    + "<rate_name>rate</rate_name>"
                + "</rate>"
            + "</client>"
        + "</clients>";

    private static final String RESULT2 =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"
            + "<clients xmlns=\"http://...\"/>";

    /**
     * tests getting xml string realization with a filled list
     */
    @Test
    public void testGetXMLString() {
        XMLWriter writer = new XMLWriter();

        Client client = new Client(12, "log", "email", "surname", 123.321,
                new Rate(1, "rate"));
        String actualString = writer.getXMLString(new ArrayList<>(Collections.singletonList(client)));
        Assert.assertEquals("strings should be the same", RESULT1, actualString);
    }

    /**
     * tests getting xml string realization with an empty list
     */
    @Test
    public void testGetXMLStringEmptyList() {
        XMLWriter writer = new XMLWriter();

        String actualString = writer.getXMLString(new ArrayList<>());
        Assert.assertEquals("strings should be the same", RESULT2, actualString);
    }
}
