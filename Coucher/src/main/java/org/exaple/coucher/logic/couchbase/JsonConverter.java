package org.exaple.coucher.logic.couchbase;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import org.exaple.coucher.logic.couchbase.entities.IdBalancePair;

/**
 * converts json strings
 */
public class JsonConverter {

    /**
     * gets IdBalancePair instance from the json document
     * @param jsonObject object to convert
     * @return returns a IdBalancePair instance
     */
    public IdBalancePair getPair(JsonObject jsonObject) {
        final long id = jsonObject.getLong("client_id");
        final double balance = jsonObject.getDouble("balance");

        return new IdBalancePair(id, balance);
    }

    /**
     * gets a JsonDocument instance from the pair
     * @param pair IdBalancePair object
     * @return returns a JsonDocument instance
     */
    public JsonDocument getDocument(IdBalancePair pair) {
        JsonObject jsonObject = JsonObject.create()
                .put("client_id", pair.getId())
                .put("balance", pair.getBalance());

        return JsonDocument.create(String.valueOf(pair.getId()), jsonObject);
    }
}
