package com.example.logs.forwarding;

import java.util.Collection;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.logs.forwarding.destination.LogChannel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogForwardingService {

    private final Collection<LogChannel> logChannels;

    @Qualifier("logForwardingExecutor")
    private final ExecutorService logForwardingExecutor;

    public void forwardLogEntry(final LogEntry logEntry) {
        logChannels.forEach(logChannel ->
                logForwardingExecutor.execute(() -> logChannel.consumeLog(logEntry)));
    }
}
