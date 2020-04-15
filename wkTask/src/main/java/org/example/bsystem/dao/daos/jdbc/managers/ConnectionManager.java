package org.example.bsystem.dao.daos.jdbc.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * a singleton class for getting db connections\
 * realizes connection pool
 */
public class ConnectionManager {
    private static ConnectionManager manager;
    private final ResourceBundle resourceBundle;
    private final List<Connection> connections;
    private final String url;
    private final Properties configuration;

    /**
     * creates a connection pool
     */
    private ConnectionManager() {
        resourceBundle = ResourceBundle.getBundle("data_base");
        url = resourceBundle.getString("db.url");
        configuration = getConnectionConfig();
        try {
            Class.forName(resourceBundle.getString("driver"));
        } catch (ClassNotFoundException e) {
            // TODO:  to log
        }
        final int poolCapacity = Integer.parseInt(resourceBundle.getString("db.pool"));
        connections = new ArrayList<>();
        for (int connectionIndex = 0; connectionIndex < poolCapacity; connectionIndex++) {
            connections.add(createConnection());
        }
    }

    /**
     * gets a connection manager
     * is synchronized
     * @return returns a connection manager instance
     */
    public static synchronized ConnectionManager getManager() {
        if (manager == null)
            manager = new ConnectionManager();

        return manager;
    }

    /**
     * gets a connection from a pool
     * if a pool is empty, creates a new connection
     * @return return a connection
     */
    public Connection getConnection() {
        if (connections.isEmpty())
            return createConnection();
        return connections.remove(connections.size() - 1);
    }

    /**
     * puts a connection back into a pool
     * @param connection a connection to release
     */
    public void releaseConnection(Connection connection) {
        connections.add(connection);
    }

    /**
     * creates a connection
     * @return returns a connection instance, can be null
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, configuration);
        }
        catch (SQLException e) {
            // TODO: to log
        }
        return connection;
    }

    /**
     * gets the full db configuration
     * @return returns properties with a configuration
     */
    private Properties getConnectionConfig() {
        Properties configuration = new Properties();
        configuration.put("user", resourceBundle.getString("db.user"));
        configuration.put("password", resourceBundle.getString("db.password"));
        configuration.put("serverTimezone", resourceBundle.getString("db.serverTimezone"));
        configuration.put("characterEncoding", resourceBundle.getString("db.characterEncoding"));
        configuration.put("useUnicode", resourceBundle.getString("db.useUnicode"));
        return configuration;
    }
}
