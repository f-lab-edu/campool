package com.campool.configuration;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class CacheConfiguration {


    @Value("${campool.redis.cache.host}")
    private String cacheHost;

    @Value("${campool.redis.cache.port}")
    private int cachePort;

    @Bean
    public RedisConnectionFactory cacheConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(cacheHost);
        redisStandaloneConfiguration.setPort(cachePort);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory cacheConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(cacheConnectionFactory);
        return redisTemplate;
    }

    @Bean
    RedisCacheManager cacheManager(RedisConnectionFactory cacheConnectionFactory) {
        return RedisCacheManager.builder(cacheConnectionFactory).cacheDefaults(
                RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(
                        fromSerializer(new GenericJackson2JsonRedisSerializer()))).build();
    }

}
