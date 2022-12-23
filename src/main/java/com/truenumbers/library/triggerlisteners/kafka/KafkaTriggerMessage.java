package com.truenumbers.library.triggerlisteners.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import com.truenumbers.library.triggerapi.models.TriggerExecutionType;
import com.truenumbers.library.truenumbersapi.Truenumber;

import java.io.IOException;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KafkaTriggerMessage {
    protected String id;
    protected TriggerExecutionType triggeredOn;
    protected List<Truenumber> truenumbers;

    public static KafkaTriggerMessage fromKafkaConsumerRecord (ConsumerRecord<String, String> consumerRecord) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(consumerRecord.value(), KafkaTriggerMessage.class);
    }
}
