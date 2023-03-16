package com.example.logs.forwarding.destination;

import com.example.logs.forwarding.LogEntry;

public interface LogChannel {

    void consumeLog(LogEntry logEntry);
}
