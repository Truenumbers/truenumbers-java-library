package org.truenumbers.triggerlisteners.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.truenumbers.triggerapi.models.TriggerExecutionType;
import org.truenumbers.truenumbersapi.Truenumber;

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