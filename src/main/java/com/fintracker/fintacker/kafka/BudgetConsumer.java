package com.fintracker.fintacker.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BudgetConsumer {
    public static final Logger log = LoggerFactory.getLogger(BudgetConsumer.class);


    @KafkaListener(topics = "transaction-events", groupId = "fintracker-group")
    public void consume(String message) {
        String[] parts = message.split(":");
        String userIdStr = parts[0];
        String categoryIdStr = parts[1];
        BigDecimal amount = new BigDecimal(parts[2]);

        Long userId = userIdStr.equals("null") ? null : Long.parseLong(userIdStr);
        Long categoryId = categoryIdStr.equals("null") ? null : Long.parseLong(categoryIdStr);

        log.info("Получено событие: userId={}, categoryId={}, amount={}", userId, categoryId, amount);
    }
}
