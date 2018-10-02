package org.drpsy.spittr.config;

import static java.util.Collections.singletonMap;
import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;

import java.time.Duration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by drpsy on 06-Apr-18 (21:40).
 *
 * Enables Spring's support for annotation driven caching (e.g. @Cacheable). Creates an aspect with pointcuts that
 * trigger off of.
 * Spring's caching annotations.
 */
@Configuration
@EnableCaching
public class CachingConfig {

  @Bean
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisCF) {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisCF);
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  @Bean
  public RedisConnectionFactory connectionFactory() {
    JedisConnectionFactory jedisConnectionFactory
        = new JedisConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
    jedisConnectionFactory.afterPropertiesSet();

    return jedisConnectionFactory;
  }

  /**
   * Cache managers are the heart of Spring’s cache abstraction, enabling integration with one of several popular
   * caching implementations.
   * @param connectionFactory
   * @return
   */
  @Bean
  public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {

    return RedisCacheManager.builder(connectionFactory)
        .cacheDefaults(defaultCacheConfig())
        .withInitialCacheConfigurations(
            singletonMap("predefined",
                defaultCacheConfig()
                    .disableCachingNullValues()
                    .entryTtl(Duration.ofSeconds(30))
            )
        )
        .transactionAware()
        .build();
  }

}
