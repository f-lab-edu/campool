package com.campool.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class SessionConfiguration {

    @Value("${campool.redis.session.host}")
    private String sessionHost;

    @Value("${campool.redis.session.port}")
    private int sessionPort;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(sessionHost);
        redisStandaloneConfiguration.setPort(sessionPort);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

}
