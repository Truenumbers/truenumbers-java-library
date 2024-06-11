package com.truenumbers.triggerlisteners.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaTriggerListener {

    protected KafkaConsumer kafkaConsumer;

    public KafkaTriggerListener (KafkaConsumer consumer) {
        this.kafkaConsumer = consumer;
    }

}
