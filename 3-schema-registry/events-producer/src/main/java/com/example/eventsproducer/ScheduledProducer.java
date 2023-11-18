package com.example.eventsproducer;

import com.github.javafaker.Faker;
import org.example.avro.Account;
import org.example.avro.AccountType;
import org.example.avro.NewCustomerCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;

@Component
public class ScheduledProducer {
    private static final Logger logger = LoggerFactory.getLogger(EventsProducerApplication.class);
    private KafkaTemplate<String, NewCustomerCreatedEvent> kafkaTemplate;

    public ScheduledProducer(KafkaTemplate<String, NewCustomerCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 2000)
    public void generatePayload() {
        NewCustomerCreatedEvent event = newCustomer(new Faker());
        logger.info("sending to topic '{}' the event '{}'", Config.TOPIC, event);
        kafkaTemplate.send(Config.TOPIC, event);
    }

    private static NewCustomerCreatedEvent newCustomer(Faker faker) {
        return NewCustomerCreatedEvent.newBuilder()
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setAccounts(Arrays.asList(
                        Account.newBuilder()
                                .setIban("CH93 0076 2011 6238 5295 7")
                                .setIban(faker.finance().iban())
                                .setType(AccountType.CHECKING)
                                .build(),
                        Account.newBuilder()
                                .setIban(faker.finance().iban())
                                .setType(AccountType.SAVING)
                                .build()
                ))
                .setSettings(new HashMap<String, Boolean>() {{
                    put("e-billing-enabled", faker.bool().bool());
                    put("push-notification-enabled", faker.bool().bool());
                }})
                .setSignupTimestamp(Instant.now())
                .build();
    }

}
