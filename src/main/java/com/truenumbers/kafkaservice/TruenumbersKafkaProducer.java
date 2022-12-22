package com.truenumbers.kafkaservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.truenumbers.kafkaservice.models.BatchCreateTruenumbersMessage;
import com.truenumbers.kafkaservice.models.TagTruenumbersMessage;
import com.truenumbers.utils.TnApiException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class TruenumbersKafkaProducer {

    protected KafkaProducer producer;

    protected String createTruenumbersTopic = "CREATE_TRUENUMBERS_TOPIC";
    protected String tagTruenumbersTopic = "TAG_TRUENUMBERS_TOPIC";
    protected String removeTruenumberTagsTopic = "REMOVE_TAG_TRUENUMBERS_TOPIC";

    public TruenumbersKafkaProducer(KafkaProducer producer) {
        this.producer = producer;
    }

    public TruenumbersKafkaProducer(KafkaProducer producer, String createTruenumbersTopic, String tagTruenumbersTopic, String removeTruenumberTagsTopic) {
        this(producer);
        this.createTruenumbersTopic = createTruenumbersTopic;
        this.tagTruenumbersTopic = tagTruenumbersTopic;
        this.removeTruenumberTagsTopic = removeTruenumberTagsTopic;
    }

    public void batchCreateTruenumbers (BatchCreateTruenumbersMessage message) throws JsonProcessingException, TnApiException {
        if (message.getNumberspace().isEmpty()) {
            throw new TnApiException("Numberspace is empty", "1");
        }

        if (message.getTruespeak().isEmpty() && message.getTruenumbers().size() < 1) {
            throw new TnApiException("No truemuber data to create", "2");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(message);
        ProducerRecord record = new ProducerRecord(createTruenumbersTopic, body);
        producer.send(record);
    }

    public void tagTruenumbers(TagTruenumbersMessage message) throws JsonProcessingException, TnApiException {
        if (message.getNumberspace().isEmpty()) {
            throw new TnApiException("Numberspace is empty", "1");
        }

        if (message.getTags().size() < 1) {
            throw new TnApiException("Tags is empty", "3");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(message);
        ProducerRecord record = new ProducerRecord(tagTruenumbersTopic, body);
        producer.send(record);
    }

    public void removeTruenumberTags(TagTruenumbersMessage message) throws JsonProcessingException, TnApiException {
        if (message.getNumberspace().isEmpty()) {
            throw new TnApiException("Numberspace is empty", "1");
        }

        if (message.getTags().size() < 1) {
            throw new TnApiException("Tags is empty", "4");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(message);
        ProducerRecord record = new ProducerRecord(removeTruenumberTagsTopic, body);
        producer.send(record);
    }
}
