package com.example.logs.forwarding;

import static com.example.logs.forwarding.LogController.PATH;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(PATH)
@RequiredArgsConstructor
public class LogController {

    public static final String PATH = "/api/logs";

    private final LogForwardingService logForwardingService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void forwardLog(@RequestBody @Valid LogEntry logEntry) {
        logForwardingService.forwardLogEntry(logEntry);
    }

    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void forwardLogs(@RequestBody @Valid List<LogEntry> logEntries) {
        logEntries.forEach(logForwardingService::forwardLogEntry);
    }
}
