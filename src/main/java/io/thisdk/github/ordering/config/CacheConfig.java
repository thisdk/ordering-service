package io.thisdk.github.ordering.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheConfig {
    private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);
    protected String host;
    protected String password;
    protected int port;
    protected int database = 0;
    private static boolean isCluster;
    private static CacheConfig cacheConfig;

    public static CacheConfig getInstance() {
        try {
            if (null == cacheConfig) {
                cacheConfig = new CacheConfig();
            }
        } catch (Exception var1) {
            logger.warn(var1.getMessage(), var1);
        }

        return cacheConfig;
    }

    private CacheConfig() {
    }

    public static boolean isCluster() {
        return isCluster;
    }

    public void setCluster(boolean cluster) {
        isCluster = cluster;
    }

    public int getDatabase() {
        return this.database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
        this.setCluster(host.contains(","));
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}