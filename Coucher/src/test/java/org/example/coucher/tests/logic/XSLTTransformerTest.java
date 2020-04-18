package org.example.coucher.tests.logic;

import org.exaple.coucher.logic.XSLTTransformer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * tests an xslt transformer class
 */
public class XSLTTransformerTest {
    private static final String INITIAL_XML = "<clients>\n" +
            "<client>\n" +
            "<client_id>16</client_id>\n" +
            "<login>login15</login>\n" +
            "<email>login15@gmail.com</email>\n" +
            "<surname>surname15</surname>\n" +
            "<balance>975512.0</balance>\n" +
            "<rate>\n" +
            "<rate_id>1</rate_id>\n" +
            "<rate_name>rate0</rate_name>\n" +
            "</rate>\n" +
            "</client>\n" +
            "<client>\n" +
            "<client_id>23</client_id>\n" +
            "<login>login22</login>\n" +
            "<email>login22@gmail.com</email>\n" +
            "<surname>surname22</surname>\n" +
            "<balance>71780.0</balance>\n" +
            "<rate>\n" +
            "<rate_id>1</rate_id>\n" +
            "<rate_name>rate0</rate_name>\n" +
            "</rate>\n" +
            "</client>\n" +
            "</clients>";

    @Test
    public void testGetTransformed() {
        String path = getClass().getClassLoader().getResource("clients.xsl").getPath();
        XSLTTransformer transformer = new XSLTTransformer(path);

        Scanner scanner = new Scanner(transformer.getTransformed(INITIAL_XML));
        ArrayList<String> resultStrings = new ArrayList<>();
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            resultStrings.add(scanner.nextLine());
        }

        Object[] expecteds = new String[] {"16/975512.0", "23/71780.0"};
        Object[] actuals = resultStrings.toArray();

        Assert.assertArrayEquals("strings should be [16/975512.0, 23/71780.0]", expecteds, actuals);
    }
}
