package com.example.logs.forwarding;

import java.util.Collection;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.logs.forwarding.destination.LogChannel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogForwardingService {

    private final Collection<LogChannel> logChannels;

    @Async
    public void forwardLogEntry(final LogEntry logEntry) {
        logChannels.forEach(logChannel -> logChannel.consumeLog(logEntry));
    }
}
