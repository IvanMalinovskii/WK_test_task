package org.example.bsystem.logic.services;

import org.example.bsystem.dao.entities.Client;
import org.example.bsystem.dao.factories.StaticDaoFactory;
import org.example.bsystem.dao.factories.interfaces.DaoFactory;
import org.example.bsystem.dao.interfaces.ClientDao;
import org.example.bsystem.logic.parsers.JSONWriter;
import org.example.bsystem.logic.parsers.XMLWriter;

import java.util.List;

/**
 * gets clients and converts them into an input format
 */
public class GettingClientsService {
    private ClientDao clientDao;
    private XMLWriter xmlWriter;
    private JSONWriter jsonWriter;

    /**
     * initializes fileds
     */
    public GettingClientsService() {
        DaoFactory factory = StaticDaoFactory.getDaoFactory("JDBC");
        clientDao = factory.getClientDao();
        xmlWriter = new XMLWriter();
        jsonWriter = new JSONWriter();
    }

    /**
     * gets client in the particular format
     * @param contentType result content type
     * @return returns a string with a result
     */
    public String getResult(String contentType) {
        List<Client> clients = clientDao.getAllEntities();

        switch (contentType) {
            case "xml":
                return xmlWriter.getXMLString(clients);
            case "json":
                return jsonWriter.getJsonString(clients);
            default:
                throw new RuntimeException("invalid content type");
        }
    }
}
