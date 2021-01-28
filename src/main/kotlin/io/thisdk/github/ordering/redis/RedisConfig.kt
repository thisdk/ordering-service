package io.thisdk.github.ordering.redis

import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.cache.RedisCacheWriter
import org.springframework.data.redis.connection.RedisConnectionFactory
import java.time.Duration

@Configuration
class RedisConfig : CachingConfigurerSupport() {

    companion object {
        const val REDIS_KEY_DATABASE = "ordering"
    }

    @Bean
    fun redisCacheManager(redisConnectionFactory: RedisConnectionFactory): RedisCacheManager {
        val redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory)
        val redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(30))
        return RedisCacheManager(redisCacheWriter, redisCacheConfiguration)
    }

}