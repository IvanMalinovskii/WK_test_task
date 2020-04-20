package org.exaple.coucher.logic.interfaces;

/**
 * does a request for getting data
 */
public interface Request {
    /**
     * does a request
     * @param contentType content preferred to be sent
     * @return returns a string response
     */
    String doRequest(String contentType);
}
