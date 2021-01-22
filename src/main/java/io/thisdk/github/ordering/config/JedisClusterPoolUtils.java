package io.thisdk.github.ordering.config;

import com.alibaba.fastjson.JSON;
import io.thisdk.github.ordering.exception.ServiceException;
import io.thisdk.github.ordering.utils.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JedisClusterPoolUtils {
    private static Logger logger = LoggerFactory.getLogger(JedisClusterPoolUtils.class);
    private static JedisCluster jedisCluster = null;

    public JedisClusterPoolUtils() {
    }

    private static void createJedisPool() {
        try {
            String[] ipArray = getClusterIps();
            if (StringUtils.isEmpty(ipArray)) {
                throw new ServiceException("ipArray is null");
            }

            Set<HostAndPort> jedisClusterNodes = new HashSet();

            for(int i = 0; i < ipArray.length; ++i) {
                String[] items = ipArray[i].split(":");
                jedisClusterNodes.add(new HostAndPort(items[0], Integer.parseInt(items[1])));
            }

            GenericObjectPoolConfig config = new GenericObjectPoolConfig();
            config.setMaxTotal(500);
            config.setMaxIdle(250);
            config.setMinIdle(100);
            config.setMaxWaitMillis(5000L);
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
            jedisCluster = new JedisCluster(jedisClusterNodes, config);
            if (null != jedisCluster) {
                logger.warn("Connent Redis Cluster:  " + JSON.toJSONString(jedisClusterNodes) + " is Success...");
            }
        } catch (Exception var4) {
            var4.printStackTrace();
            logger.info("初始化 redis 出错啦...");
        }

    }

    public static boolean isSuccess() {
        return StringUtils.isNotEmpty(jedisCluster);
    }

    private static String[] getClusterIps() {
        String host = CacheConfig.getInstance().getHost();
        return host.split(",");
    }

    private static synchronized void poolInit() {
        if (jedisCluster == null) {
            createJedisPool();
        }

    }

    public static JedisCluster getJedisCluster() {
        if (jedisCluster == null) {
            poolInit();
        }

        return jedisCluster;
    }

    public static void close() {
        if (jedisCluster != null) {
            try {
                jedisCluster.close();
            } catch (IOException var1) {
                logger.warn(var1.getMessage(), var1);
            }
        }

    }
}