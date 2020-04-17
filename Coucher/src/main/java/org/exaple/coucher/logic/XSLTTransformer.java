package org.exaple.coucher.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * transforms XML files via XSLT
 */
public class XSLTTransformer {
    private static final Logger LOGGER = LogManager.getLogger(XSLTTransformer.class.getName());
    private Transformer transformer;

    /**
     * initializes a transformer
     */
    public XSLTTransformer(String xsltDocumentPath) {
        try {
            Source xsltSource = new StreamSource(new File(xsltDocumentPath));
            transformer = TransformerFactory.newInstance().newTransformer(xsltSource);
        } catch (TransformerConfigurationException e) {
            LOGGER.error(LOGGER.getName() + " - can't initialize transformer: " + e);
        }
    }

    public String getTransformed(String initialXML) {
        Source initialXmlSource = new StreamSource(new StringReader(initialXML));
        StringWriter xmlResult = new StringWriter();
        try {
            transformer.transform(initialXmlSource, new StreamResult(xmlResult));
            return xmlResult.getBuffer().toString();
        } catch (TransformerException e) {
            LOGGER.error(LOGGER.getName() + " - cant't transform a document" + e);
        }
        return null;
    }
}
