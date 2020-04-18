package org.exaple.coucher.logic.couchbase.managers;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;

/**
 * a singleton class to get couchbase buckets
 */
public class CouchBaseConnector {
    private static CouchBaseConnector connector;
    private Cluster cluster;
    private Bucket bucket;

    /**
     * initializes a cluster and a bucket
     */
    private CouchBaseConnector() {
        final PropertyManager properties =  PropertyManager.getManager();
        cluster = CouchbaseCluster.create(properties.getProperty("cb.cluster"));
        cluster.authenticate(properties.getProperty("cb.login"), properties.getProperty("cb.password"));
        bucket = cluster.openBucket(properties.getProperty("cb.bucket"));
    }

    /**
     * gets a connector instance
     * is synchronized
     * @return returns a connector instance
     */
    public static synchronized CouchBaseConnector getConnector() {
        if (connector == null) {
            connector = new CouchBaseConnector();
        }
        return connector;
    }

    /**
     * gets a bucket
     * @return returns an instance of a bucket
     */
    public Bucket getBucket() {
        return bucket;
    }

    /**
     * close a bucket and a cluster before GC collection
     * @throws Throwable throwable
     */
    @Override
    protected void finalize() throws Throwable {
        bucket.close();
        cluster.disconnect();
        super.finalize();
    }
}
