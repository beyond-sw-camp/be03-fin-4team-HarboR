package com.example.harbor_total.global.kafka;

import com.example.harbor_total.domain.Employee;
import com.example.harbor_total.repository.EmployeeRepository;
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
public class KafkaListenerList {
    private final ObjectMapper objectMapper;
    private final EmployeeRepository employeeRepository;

    public KafkaListenerList(ObjectMapper objectMapper, EmployeeRepository employeeRepository) {
        this.objectMapper = objectMapper;
        this.employeeRepository = employeeRepository;
    }

    @KafkaListener(topics = "first_create_user_data", groupId = "group_2")
    public void createMemberListener(String data) {
        log.info("Kafka Message : -> " + data);

        Map<String, String> map = new HashMap<>();
        try{
            map = objectMapper.readValue(data, new TypeReference<Map<String, String>>() {});
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        employeeRepository.save(Employee.create(map.get("employeeId"), map.get("email"), map.get("name"), map.get("teamCode"), 15));
    }
}