package com.example.logs.forwarding.destination;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.example.logs.forwarding.LogEntry;

@ExtendWith(MockitoExtension.class)
class MongoLogChannelTest {

    @Mock
    MongoTemplate mongoTemplate;

    @InjectMocks
    MongoLogChannel mongoLogChannel;

    @Test
    void shouldConvertLogEntryIntoDbObject() {
        final var logEntry = new LogEntry(
                "twill_pricing_service",
                "random124",
                "WARN",
                1678952953l,
                "Sth irritating happen.",
                "",
                "request_124"
        );

        mongoLogChannel.consumeLog(logEntry);

        verify(mongoTemplate).save(DBLogEntry.builder()
                .applicationID("twill_pricing_service")
                .traceId("random124")
                .severity("WARN")
                .timestamp(1678952953l)
                .message("Sth irritating happen.")
                .componentName("")
                .requestId("request_124")
                .build()
        );
    }
}