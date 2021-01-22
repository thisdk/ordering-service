package io.thisdk.github.ordering.config;

public class CachePlugin {
    public CachePlugin(String endpoint, String pwd, int database) {
        String[] arrayItem = endpoint.split(",");
        if (arrayItem.length > 1) {
            CacheConfig.getInstance().setHost(endpoint);
            CacheConfig.getInstance().setPort(0);
        } else {
            String[] endpointArray = endpoint.split(":");
            CacheConfig.getInstance().setHost(endpointArray[0]);
            CacheConfig.getInstance().setPort(Integer.parseInt(endpointArray[1]));
        }

        CacheConfig.getInstance().setDatabase(database);
        CacheConfig.getInstance().setPassword(pwd);
    }

    public CachePlugin(String host, int port, String pwd, int database) {
        CacheConfig.getInstance().setHost(host);
        CacheConfig.getInstance().setPort(port);
        CacheConfig.getInstance().setDatabase(database);
        CacheConfig.getInstance().setPassword(pwd);
    }

    public CachePlugin(String host, int port) {
        CacheConfig.getInstance().setHost(host);
        CacheConfig.getInstance().setPort(port);
    }

    public void init() throws Exception {
    }

    public void start() throws Exception {
        if (!CacheConfig.isCluster()) {
            JedisPoolUtils.getJedis();
        } else {
            JedisClusterPoolUtils.getJedisCluster();
        }

    }

    public void stop() throws Exception {
        if (!CacheConfig.isCluster()) {
            JedisPoolUtils.close();
        } else {
            JedisClusterPoolUtils.close();
        }

    }
}