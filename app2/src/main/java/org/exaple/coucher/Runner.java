package org.exaple.coucher;

import org.exaple.coucher.logic.GetHttpRequest;
import org.exaple.coucher.logic.XSLTTransformer;
import org.exaple.coucher.logic.couchbase.daos.ClientsDao;
import org.exaple.coucher.logic.couchbase.daos.CouchBaseClientsDao;
import org.exaple.coucher.logic.couchbase.managers.PropertyManager;
import org.exaple.coucher.logic.interfaces.AMQPSender;
import org.exaple.coucher.logic.interfaces.Request;
import org.exaple.coucher.logic.interfaces.Transformer;
import org.exaple.coucher.logic.rabbitmq.RabbitMQSender;
import org.exaple.coucher.services.AMQPService;
import org.exaple.coucher.services.CacheService;
import org.exaple.coucher.services.Service;

import java.util.Timer;
import java.util.TimerTask;

/**
 * runs an application
 */
public class Runner {
    private static final PropertyManager properties = PropertyManager.getManager();
    private static Service cacheService;
    private static Service amqpService;

    /**
     * the main method: starts sets up services, starts timers
     * @param args input args string
     */
    public static void main(String[] args) {
        int cacheTime = Integer.parseInt(properties.getProperty("time.cache"));
        int brokerTime = Integer.parseInt(properties.getProperty("time.broker"));

        if (args.length > 2) {
            cacheTime = Integer.parseInt(args[0]);
            brokerTime = Integer.parseInt(args[1]);
        }
        initializeServices();
        startTimers(cacheTime, brokerTime);

        while (true){

        }
    }

    private static void initializeServices() {
        String path = Runner.class.getClassLoader().getResource(properties.getProperty("xsl.name")).getPath();
        Request request = new GetHttpRequest(properties.getProperty("http.url"));
        Transformer transformer = new XSLTTransformer(path);
        ClientsDao clientsDao = new CouchBaseClientsDao();
        AMQPSender sender = new RabbitMQSender();

        cacheService = new CacheService(request, transformer, clientsDao);
        amqpService = new AMQPService(clientsDao, sender);
    }

    private static TimerTask setUpTask(Service service) {
        return new TimerTask() {
            @Override
            public void run() {
                    service.doService();
            }
        };
    }

    private static void startTimers(int cacheTime, int brokerTime) {
        Timer cacheTimer = new Timer(true);
        cacheTimer.scheduleAtFixedRate(setUpTask(cacheService), 0, cacheTime * 1000);
        Timer brokerTimer = new Timer(true);
        brokerTimer.scheduleAtFixedRate(setUpTask(amqpService), 0, brokerTime * 1000);
    }
}
