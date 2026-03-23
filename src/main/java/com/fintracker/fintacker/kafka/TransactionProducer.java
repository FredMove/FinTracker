package com.fintracker.fintacker.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "transaction-events";

    public TransactionProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTransaction(Long userId, Long categoryId, BigDecimal amount){
        String message = userId + ":" + categoryId + ":" + amount;
        kafkaTemplate.send(TOPIC, message);
    }
}
