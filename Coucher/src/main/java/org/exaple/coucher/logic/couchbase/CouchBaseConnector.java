package org.exaple.coucher.logic.couchbase;

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

    private CouchBaseConnector() {
        cluster = CouchbaseCluster.create("127.0.0.1");
        cluster.authenticate("admin", "password");
        bucket = cluster.openBucket("buck");
    }

    public static synchronized CouchBaseConnector getConnector() {
        if (connector == null) {
            connector = new CouchBaseConnector();
        }
        return connector;
    }

    public Bucket getBucket() {
        return bucket;
    }

    @Override
    protected void finalize() throws Throwable {
        bucket.close();
        cluster.disconnect();
        super.finalize();
    }
}
