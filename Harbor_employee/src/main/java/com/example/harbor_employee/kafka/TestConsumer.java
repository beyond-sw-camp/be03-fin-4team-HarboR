package com.example.harbor_employee.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class TestConsumer {
    private final ObjectMapper objectMapper;

    public TestConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "first_create_user_data", groupId = "group_1")
    public void listener(String data) {
        log.info("Kafka Message : -> " + data);

        Map<String, String> map = new HashMap<>();
        try{
            map = objectMapper.readValue(data, new TypeReference<Map<String, String>>() {});
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        String email = map.get("email");
        String name = map.get("name");

        System.out.println("email: " + email);
        System.out.println("name: " + name);
    }
    }