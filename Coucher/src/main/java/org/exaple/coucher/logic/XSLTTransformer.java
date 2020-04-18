package org.exaple.coucher.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * transforms XML files via XSLT
 */
public class XSLTTransformer implements org.exaple.coucher.logic.interfaces.Transformer {
    private static final Logger LOGGER = LogManager.getLogger(XSLTTransformer.class.getName());
    private Transformer transformer;

    /**
     * initializes a transformer
     */
    public XSLTTransformer(String xsltDocumentPath) {
        try {
            final Source xsltSource = new StreamSource(new File(xsltDocumentPath));
            transformer = TransformerFactory.newInstance().newTransformer(xsltSource);
        } catch (TransformerConfigurationException e) {
            LOGGER.error(LOGGER.getName() + " - can't initialize transformer: " + e);
        }
    }

    /**
     * transforms an input XML string into a key/pair values string
     * @param initialXML an XML string to transform
     * @return returns a transformed string
     */
    public String getTransformed(String initialXML) {
        final Source initialXmlSource = new StreamSource(new StringReader(initialXML));
        final StringWriter xmlResult = new StringWriter();
        try {
            transformer.transform(initialXmlSource, new StreamResult(xmlResult));
            return xmlResult.getBuffer().toString();
        } catch (TransformerException e) {
            LOGGER.error(LOGGER.getName() + " - cant't transform a document" + e);
        }
        return null;
    }
}
