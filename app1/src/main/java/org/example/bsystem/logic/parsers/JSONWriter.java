package org.example.bsystem.logic.parsers;

import org.example.bsystem.dao.entities.Client;
import org.example.bsystem.dao.entities.Rate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * converts client entities into a json string
 */
@SuppressWarnings("unchecked")
public class JSONWriter {

    /**
     * gets a json string from a clients list
     * @param clients clients to convert
     * @return returns a json string with a result
     */
    public String getJsonString(List<Client> clients) {
        JSONArray clientObjects = new JSONArray();
        clients.forEach(client -> {
            JSONObject clientObject = new JSONObject();
            clientObject.put("client", getJsonClient(client));
            clientObjects.add(clientObject);
        });
        JSONObject result = new JSONObject();
        result.put("clients", clientObjects);

        return result.toJSONString();
    }

    private JSONObject getJsonClient(Client client) {
        JSONObject clientObject = new JSONObject();
        clientObject.put("client_id", client.getId());
        clientObject.put("login", client.getLogin());
        clientObject.put("email", client.getEmail());
        clientObject.put("surname", client.getSurname());
        clientObject.put("balance", client.getBalance());
        clientObject.put("rate", getJsonRate(client.getRate()));

        return clientObject;
    }

    private JSONObject getJsonRate(Rate rate) {
        JSONObject rateObject = new JSONObject();
        rateObject.put("rate_id", rate.getId());
        rateObject.put("rate_name", rate.getRate());

        return rateObject;
    }
}
