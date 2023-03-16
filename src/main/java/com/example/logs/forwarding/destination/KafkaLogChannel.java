package com.example.logs.forwarding.destination;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.logs.forwarding.LogEntry;

@Component
class KafkaLogChannel implements LogChannel {

    private final KafkaTemplate kafkaTemplate;

    private final String kafkaTopic;

    KafkaLogChannel(
            final KafkaTemplate kafkaTemplate,
            @Value("${example.logs.kafka.topic}") final String kafkaTopic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopic = kafkaTopic;
    }

    @Override
    public void consumeLog(final LogEntry logEntry) {
        kafkaTemplate.send(kafkaTopic, logEntry.applicationID(), logEntry);
    }
}
