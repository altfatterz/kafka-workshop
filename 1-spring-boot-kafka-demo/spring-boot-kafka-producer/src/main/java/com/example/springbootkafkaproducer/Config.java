package com.example.springbootkafkaproducer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("docker")
public class Config {

    public static final String TOPIC = "my-topic";

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name(TOPIC)
                .partitions(6)
                .replicas(1)
                .build();
    }


}
