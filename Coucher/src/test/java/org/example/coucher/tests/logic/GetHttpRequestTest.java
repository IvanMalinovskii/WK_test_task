package org.example.coucher.tests.logic;


import org.exaple.coucher.logic.GetHttpRequest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class GetHttpRequestTest {
    @Test
    public void testDoRequestJSON() {
        GetHttpRequest request = new GetHttpRequest("http://localhost:8080/bsystem/clients");

        Assert.assertNotNull("response string shouldn't be null", request.doRequest("application/json"));
    }

    @Test
    public void testDoRequestXML() {
        GetHttpRequest request = new GetHttpRequest("http://localhost:8080/bsystem/clients");
        Assert.assertNotNull("response string shouldn't be null", request.doRequest("text/xml"));
    }
}
