package com.example.springbootkafkaconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(groupId = "my-group", topics = Config.TOPIC, clientIdPrefix = "${spring.application.name}", concurrency = "3")
    public void consume(String message) {
        if (message.startsWith("poison-pill")) {
            throw new RuntimeException("failed processing message:" + message);
        }
        logger.info("Consumed message -> {}", message);
    }

    @KafkaListener(groupId = "my-group-dlt", topics = Config.TOPIC_DLT, clientIdPrefix = "${spring.application.name}")
    public void dltConsume(String message) {
        logger.info("Received from {} : {}", Config.TOPIC_DLT, message);
    }


}
