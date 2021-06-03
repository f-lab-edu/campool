package com.campool.configuration;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.data.redis.RedisSessionRepository;

@EnableSpringHttpSession
@Configuration
public class SessionConfiguration {

    public static final long MAX_INACTIVE_INTERVAL = 300;

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

    @Bean
    public RedisOperations<String, Object> sessionRedisOperations(
            RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        return template;
    }

    @Bean
    public RedisSessionRepository sessionRepository(
            RedisOperations<String, Object> sessionRedisOperations) {
        RedisSessionRepository redisSessionRepository = new RedisSessionRepository(
                sessionRedisOperations);
        redisSessionRepository.setDefaultMaxInactiveInterval(Duration.ofSeconds(MAX_INACTIVE_INTERVAL));
        return redisSessionRepository;
    }

}
