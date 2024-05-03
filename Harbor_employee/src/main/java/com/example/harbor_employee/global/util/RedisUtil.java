package com.example.harbor_employee.global.util;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisUtil {

    private final StringRedisTemplate template;

    public RedisUtil(StringRedisTemplate template) {
        this.template = template;
    }

    public String getData(String key) {
        ValueOperations<String, String> valueOperations = template.opsForValue();
        return valueOperations.get(key);
    }

    public void setDataExpire(String key, String value) {
        ValueOperations<String, String> valueOperations = template.opsForValue();
        valueOperations.set(key, value);
    }

    public void deleteData(String key) {
        template.delete(key);
    }
}