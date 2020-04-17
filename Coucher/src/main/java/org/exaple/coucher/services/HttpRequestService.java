package org.exaple.coucher.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * does http requests and puts gotten data to the couchbase
 */
public class HttpRequestService {


    public String doRequest() {
        try {
            URL url = new URL("http://localhost:8080/bsystem/clients");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "text/xml");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null)
                    stringBuilder.append(line);
                return stringBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
