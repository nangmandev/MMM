package com.spring.mmm.common.service;

import java.util.Optional;

public interface RedisRepository {
    <T> boolean saveData(String key, T data);
    <T> Optional<T> getData(String key, Class<T> classType);
}
