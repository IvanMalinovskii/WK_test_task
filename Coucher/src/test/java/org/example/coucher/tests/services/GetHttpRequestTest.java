package org.example.coucher.tests.services;


import org.exaple.coucher.logic.GetHttpRequest;
import org.exaple.coucher.services.HttpRequestService;
import org.junit.Test;
import org.junit.Assert;

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
