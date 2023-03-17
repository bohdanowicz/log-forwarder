package com.example.logs.forwarding.destination;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.logs.forwarding.LogEntry;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
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
        log.debug("LogEntry [{}] pushed to kafka.", logEntry);
    }
}
