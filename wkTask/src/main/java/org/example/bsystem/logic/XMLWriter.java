package org.example.bsystem.logic;

import org.example.bsystem.dao.entities.Client;
import org.example.bsystem.dao.entities.Rate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.List;

/**
 * converts client entities into XML string
 */
public class XMLWriter {
    private DocumentBuilder documentBuilder;
    private Transformer transformer;

    /**
     * initializes a class
     */
    public XMLWriter() {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            documentBuilder = factory.newDocumentBuilder();

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
        }
        catch (ParserConfigurationException | TransformerConfigurationException e) {
            // TODO: to log
            e.printStackTrace();
        }

    }

    /**
     * gets an XML string form a clients list
     * @param clients clients to convert
     * @return returns an xml string with a result
     */
    public String getXMLString(List<Client> clients) {
        Document document = createFromClients(clients);
        try {
            final StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));

            return writer.getBuffer().toString();
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Document createFromClients(List<Client> clients) {
        Document document = documentBuilder.newDocument();
        Element root = document.createElementNS("http://...", "clients");
        clients.forEach(client -> root.appendChild(getClientElement(client, document)));
        document.appendChild(root);

        return document;
    }

    private Element getClientElement(Client client, Document document) {
        Element clientElement = document.createElement("client");
        Element id = document.createElement("client_id");
        id.setTextContent(String.valueOf(client.getId()));
        Element login = document.createElement("login");
        login.setTextContent(client.getLogin());
        Element email = document.createElement("email");
        email.setTextContent(client.getEmail());
        Element surname = document.createElement("surname");
        surname.setTextContent(client.getSurname());
        Element balance = document.createElement("balance");
        balance.setTextContent(String.valueOf(client.getBalance()));
        Element rateElement = getRateElement(client.getRate(), document);

        clientElement.appendChild(id);
        clientElement.appendChild(login);
        clientElement.appendChild(email);
        clientElement.appendChild(surname);
        clientElement.appendChild(balance);
        clientElement.appendChild(rateElement);

        return clientElement;
    }

    private Element getRateElement(Rate rate, Document document) {
        Element rateElement = document.createElement("rate");
        Element rateId = document.createElement("rate_id");
        rateId.setTextContent(String.valueOf(rate.getId()));
        Element rateName = document.createElement("rate_name");
        rateName.setTextContent(rate.getRate());
        rateElement.appendChild(rateId);
        rateElement.appendChild(rateName);

        return rateElement;
    }
}
