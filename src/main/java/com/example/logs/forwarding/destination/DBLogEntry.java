package com.example.logs.forwarding.destination;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Document(collection = DBLogEntry.COLLECTION_NAME)
public class DBLogEntry {

    public static final String COLLECTION_NAME = "log_entry";
    @Id
    String id;
    String applicationID;
    String traceId;
    String severity;
    Long timestamp;
    String message;
    String componentName;
    String requestId;

}
