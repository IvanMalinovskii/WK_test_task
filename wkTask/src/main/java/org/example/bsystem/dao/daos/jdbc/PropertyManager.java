package org.example.bsystem.dao.daos.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * a singleton class for getting connection settings
 * and queries
 */
public class PropertyManager {
    /**
     * a class instance for getting access to properties
     */
    private static PropertyManager manager;
    /**
     * db properties
     */
    private Properties properties;

    /**
     * initializes properties
     */
    private PropertyManager() {
        try (InputStream stream = this.getClass().getClassLoader().getResourceAsStream("data_base.properties")) {
            properties = new Properties();
            properties.load(stream);
        }
        catch (IOException e) {
            // TODO: to log
        }
    }

    /**
     * gets a property manager instance
     * is synchronized
     * @return returns a property manager instance
     */
    public synchronized static PropertyManager getManager() {
        if (manager == null)
            manager = new PropertyManager();
        return manager;
    }

    /**
     * gets a property by a key
     * @param key property's key
     * @return returns a property string, can be null
     */
    public String getProperty(String key) {
        if (!properties.containsKey(key))
            return null;

        return properties.getProperty(key);
    }
}