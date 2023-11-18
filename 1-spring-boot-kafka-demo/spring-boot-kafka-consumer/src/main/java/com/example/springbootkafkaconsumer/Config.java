package com.example.springbootkafkaconsumer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("docker")
public class Config {

    public static final String TOPIC = "my-topic";

    public static final String TOPIC_DLT = "my-topic-dlt";

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(TOPIC)
                .partitions(6)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicDLT() {
        return TopicBuilder.name(TOPIC_DLT)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
