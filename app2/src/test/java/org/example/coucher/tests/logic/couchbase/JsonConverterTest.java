package org.example.coucher.tests.logic.couchbase;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import org.exaple.coucher.logic.couchbase.JsonConverter;
import org.exaple.coucher.logic.couchbase.entities.IdBalancePair;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JsonConverterTest {
    private static JsonConverter converter;

    @BeforeAll
    static void initialize() {
        converter = new JsonConverter();
    }

    @Test
    public void testGetPair() {
        JsonObject jsonPair = JsonObject.create()
                .put("client_id", 1)
                .put("balance", 1234);

        IdBalancePair expected = new IdBalancePair(1, 1234);
        IdBalancePair actual = converter.getPair(jsonPair);

        Assert.assertEquals("pair should be the same as: " + expected, expected, actual);
    }

    @Test
    public void testGetDocument() {
        IdBalancePair pair = new IdBalancePair(1, 1234);

        JsonDocument expected = JsonDocument.create("1",
                JsonObject.create().put("client_id", 1)
                                   .put("balance", 1234.0));
        JsonDocument actual = converter.getDocument(pair);

        Assert.assertTrue("document should be the same as: " + expected, documentEquals(expected, actual));
    }

    private boolean documentEquals(JsonDocument first, JsonDocument second) {
        if (first == null || second == null) return false;
        if (first == second) return true;

        return first.id().equals(second.id())
                && first.content().getLong("client_id").equals(second.content().getLong("client_id"))
                && first.content().getDouble("balance").equals(second.content().getDouble("balance"));
    }
}
