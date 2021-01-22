package io.thisdk.github.ordering.config;

import io.thisdk.github.ordering.exception.ServiceException;
import io.thisdk.github.ordering.utils.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

public class JedisPoolUtils {
    private static JedisPool pool = null;
    private static String host;
    private static int port;
    private static int timeout = 2000;
    private static String password;
    private static int database = 0;

    public JedisPoolUtils() {
    }

    private static void createJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(250);
        config.setMinIdle(100);
        config.setMaxTotal(500);
        config.setMaxWaitMillis(5000L);
        config.setTestWhileIdle(false);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(false);
        config.setNumTestsPerEvictionRun(10);
        config.setMinEvictableIdleTimeMillis(1000L);
        config.setSoftMinEvictableIdleTimeMillis(10L);
        config.setTimeBetweenEvictionRunsMillis(10L);
        config.setLifo(false);

        try {
            database = CacheConfig.getInstance().getDatabase();
            host = CacheConfig.getInstance().getHost();
            password = CacheConfig.getInstance().getPassword();
            port = CacheConfig.getInstance().getPort();
            pool = new JedisPool(config, host, port, timeout, StringUtils.isEmpty(password) ? null : password, database);
            System.out.println("Connent  " + host + ":" + port + " database:" + database + " Redis is Success...");
        } catch (Exception var2) {
            var2.printStackTrace();
            throw new JedisException(var2.getMessage(), var2);
        }
    }

    public static boolean isSuccess() {
        return StringUtils.isNotEmpty(pool);
    }

    private static synchronized void poolInit() throws JedisException {
        if (pool == null) {
            createJedisPool();
        }

    }

    public static Jedis getJedis() {
        if (pool == null) {
            poolInit();
        }

        return pool.getResource();
    }

    public static void returnResource(Jedis jedis) {
        try {
            pool.returnResource(jedis);
        } catch (Exception var2) {
            throw new JedisException(var2.getMessage());
        }
    }

    public static void returnBrokenResource(Jedis jedis) {
        try {
            pool.returnBrokenResource(jedis);
        } catch (Exception var2) {
            throw new ServiceException(var2.getMessage());
        }
    }

    public static void close() {
        if (pool != null) {
            pool.close();
        }

    }
}