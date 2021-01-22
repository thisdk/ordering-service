package io.thisdk.github.ordering;

import io.thisdk.github.ordering.config.CachePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class InitDataRunner implements ApplicationRunner {
    @Autowired
    private RedisProperties redisProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initPlugin();
        initData();
    }

    /**
     * 初始化插件
     */
    private void initPlugin() {
        try {
            new CachePlugin(redisProperties.getRedisHost(), redisProperties.getRedisPort()).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
    }

}