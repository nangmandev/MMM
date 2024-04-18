package com.spring.mmm.common.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StringUtils;

import com.spring.mmm.common.exception.InternalServerCaughtException;

import redis.embedded.RedisServer;
import redis.embedded.exceptions.EmbeddedRedisException;
@Slf4j
@Profile({"local", "test"})
@Configuration
public class EmbeddedRedisConfig {
    private final RedisServer redisServer;

    public EmbeddedRedisConfig(@Value("${spring.redis.port}") int port) {
        redisServer = RedisServer.builder()
            .port(port)
            .setting("maxmemory 128M")
            .build();
    }

    @PostConstruct
    public void startRedis() {

        redisServer.start();

    }

    @PreDestroy
    public void stopRedis() {
        this.redisServer.stop();
    }

}
