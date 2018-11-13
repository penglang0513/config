package com.penglang.config.redis;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * session存放redis*/
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=60)
public class RedisSessionConfig {
}