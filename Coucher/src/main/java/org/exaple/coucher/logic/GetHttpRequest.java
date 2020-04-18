package org.exaple.coucher.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.exaple.coucher.logic.interfaces.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * does GET request by the url
 */
public class GetHttpRequest implements HttpRequest {
    private static final Logger LOGGER = LogManager.getLogger(GetHttpRequest.class.getName());
    private URL url;

    /**
     * initializes an url
     * @param url target url
     */
    public GetHttpRequest(String url) {
        try {
            this.url = new URL(url);
        }
        catch (MalformedURLException e) {
            LOGGER.error(LOGGER.getName() + " - can't initialize URL: " + e);
        }
    }

    /**
     * performs a request
     * @param contentType content type
     * @return return a response string with the data in the input content-type format
     */
    public String doRequest(String contentType) {
        try {
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", contentType);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                final StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                connection.disconnect();
                return builder.toString();
            }
        }
        catch (IOException e) {
            LOGGER.error(LOGGER.getName() + " - can't do request: " + e);
        }
        return null;
    }
}
