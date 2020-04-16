package org.example.bsystem.dao.daos.jdbc;

import org.example.bsystem.dao.entities.Client;
import org.example.bsystem.dao.entities.Rate;
import org.example.bsystem.dao.interfaces.ClientDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * jdbc client dao implementation
 */
public class JdbcClientDao implements ClientDao {
    private final ConnectionManager connectionManager;
    private final PropertyManager propertyManager;

    /**
     * initializes a class
     */
    public JdbcClientDao() {
        connectionManager = ConnectionManager.getManager();
        propertyManager = PropertyManager.getManager();
    }

    /**
     * gets all the entities form a data base
     * @return return a list with the values
     */
    @Override
    public List<Client> getAllEntities() {
        Connection connection = connectionManager.getConnection();
        final String query = propertyManager.getProperty("query.getAllClients");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Client> clients = new ArrayList<>();
                while(resultSet.next()) {
                    clients.add(getFromResult(resultSet));
                }
                return clients;
            }
            catch (SQLException e) {
                // TODO: to log
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            // TODO: to log
            e.printStackTrace();
        }
        connectionManager.releaseConnection(connection);
        return null;
    }

    private Client getFromResult(ResultSet resultSet) throws SQLException {
        final long id = resultSet.getLong(1);
        final String login = resultSet.getString(2);
        final String email = resultSet.getString(3);
        final String surname = resultSet.getString(4);
        final double balance = resultSet.getDouble(5);
        final long rateId = resultSet.getLong(7);
        final String rateName = resultSet.getString(8);
        final Rate rate = new Rate(rateId, rateName);
        return new Client(id, login, email, surname, balance, rate);
    }
}
