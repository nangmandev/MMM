package com.spring.mmm.common.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mmm.common.service.RedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RedisRepositoryImpl implements RedisRepository {
    private final RedisTemplate<String, String> redisTemplate;
    @Override
    public <T> boolean saveData(String key, T data) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            String value = mapper.writeValueAsString(data);
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public <T> Optional<T> getData(String key, Class<T> classType) {
        String value = redisTemplate.opsForValue().get(key);

        if(value == null){
            return Optional.empty();
        }

        try{
            ObjectMapper mapper = new ObjectMapper();
            return Optional.of(mapper.readValue(value, classType));
        } catch (Exception e){
            log.error(e.getMessage());
            return Optional.empty();
        }
    }
}
