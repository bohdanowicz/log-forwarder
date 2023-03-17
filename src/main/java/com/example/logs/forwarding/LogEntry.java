package com.example.logs.forwarding;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LogEntry(
        @NotEmpty
        String applicationID,
        @NotEmpty
        String traceId,
        @NotEmpty
        String severity,
        @NotNull
        Long timestamp,
        @NotEmpty
        String message,
        String componentName,
        String requestId) {
}
