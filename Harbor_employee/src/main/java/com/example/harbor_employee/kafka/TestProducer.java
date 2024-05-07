package com.example.harbor_employee.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static org.apache.poi.hssf.record.RecordFactory.createRecord;

@Component
@Slf4j
public class TestProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public TestProducer(KafkaTemplate<String, Object> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendToKafka(String topic, Object dto) {
        String json = "";
        try{
            json = objectMapper.writeValueAsString(dto);
        } catch(JsonProcessingException e){
            e.getMessage();
        }
        kafkaTemplate.send(topic, json);
        log.info("Kafka Producer data 전송 완료" + kafkaTemplate);
    }
}