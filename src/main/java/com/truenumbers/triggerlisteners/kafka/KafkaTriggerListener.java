package com.truenumbers.triggerlisteners.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.Collections;

public class KafkaTriggerListener {

    protected KafkaConsumer kafkaConsumer;

    public KafkaTriggerListener (KafkaConsumer consumer) {
        this.kafkaConsumer = consumer;
    }

}
