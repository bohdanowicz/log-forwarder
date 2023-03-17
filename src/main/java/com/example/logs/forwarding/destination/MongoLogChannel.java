package com.example.logs.forwarding.destination;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import com.example.logs.forwarding.LogEntry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
class MongoLogChannel implements LogChannel {

    private final MongoTemplate mongoTemplate;

    @Override
    public void consumeLog(final LogEntry logEntry) {
        mongoTemplate.save(
                DBLogEntry.builder()
                        .applicationID(logEntry.applicationID())
                        .traceId(logEntry.traceId())
                        .severity(logEntry.severity())
                        .timestamp(logEntry.timestamp())
                        .message(logEntry.message())
                        .componentName(logEntry.componentName())
                        .requestId(logEntry.requestId())
                        .build());
        log.debug("LogEntry [{}] pushed to mongo.", logEntry);
    }
}
