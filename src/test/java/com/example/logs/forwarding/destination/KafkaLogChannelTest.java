package com.example.logs.forwarding.destination;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import com.example.logs.forwarding.LogEntry;

@ExtendWith(MockitoExtension.class)
class KafkaLogChannelTest {

    private static final String KAFKA_TOPIC = "kafkaTopic";

    @Mock
    KafkaTemplate kafkaTemplate;

    KafkaLogChannel kafkaLogChannel;

    @BeforeEach
    void setup() {
        kafkaLogChannel = new KafkaLogChannel(kafkaTemplate, KAFKA_TOPIC);
    }

    @Test
    void shouldForwardLogEntryToKafka() {
        //given
        final var applicationID = "twill_booking_service";
        final var logEntry = new LogEntry(
                applicationID,
                "random123",
                "WARN",
                1678952952l,
                "Sth irritating happen.",
                "",
                "request_123"
        );
        //when
        kafkaLogChannel.consumeLog(logEntry);
        //then
        verify(kafkaTemplate).send(KAFKA_TOPIC, applicationID, logEntry);
    }
}