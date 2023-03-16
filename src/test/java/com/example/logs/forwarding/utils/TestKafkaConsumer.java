package com.example.logs.forwarding.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestKafkaConsumer {

    @Getter
    @Setter
    private CountDownLatch latch = new CountDownLatch(1);

    @Getter
    private List<ConsumerRecord> records = new ArrayList<>();

    @KafkaListener(topics = "#{'${example.logs.kafka.topic}'}")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        log.info("received payload='{}'", consumerRecord.toString());
        records.add(consumerRecord);
        latch.countDown();
    }

    public void resetData() {
        records = new ArrayList<>();
        latch = new CountDownLatch(1);
    }
}
