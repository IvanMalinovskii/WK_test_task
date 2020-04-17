package org.example.coucher.tests.logic;

import org.exaple.coucher.logic.XSLTTransformer;
import org.junit.Test;

/**
 * tests an xslt transformer class
 */
public class XSLTTransformerTest {
    @Test
    public void testGetTransformed() {
        String path = getClass().getClassLoader().getResource("clients.xsl").getPath();
        XSLTTransformer transformer = new XSLTTransformer(path);

        System.out.println(transformer.getTransformed("<clients>\n" +
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
                "</clients>"));
    }
}
