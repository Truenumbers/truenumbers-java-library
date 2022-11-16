package org.truenumbers;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.truenumbers.examples.TnApiExamples;
import org.truenumbers.examples.TriggerApiExamples;
import org.truenumbers.kafkaservice.TruenumbersKafkaProducer;
import org.truenumbers.kafkaservice.models.BatchCreateTruenumbersMessage;
import org.truenumbers.triggerlisteners.kafka.KafkaTriggerMessage;
import org.truenumbers.truenumbersapi.Truenumber;
import org.truenumbers.truenumbersapi.models.createtruenumbers.CreateTruenumberPayload;
import org.truenumbers.utils.TnApiException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class Main {

    private static String numberspace = "_system:numberspace/tyler-test";
    private static String bootstrapServers = "15.205.142.111:29092";
    private static String groupId = "test-java-lib";

    private static KafkaConsumer buildKafkaConsumer () {

        Properties properties = new Properties();

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer(properties);

        return consumer;
    }

    private static KafkaProducer    buildKafkaProducer () {

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        return producer;
    }

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, TnApiException {
        Truenumber tn = new Truenumber();
        tn.setId("id");
        tn.setSubject("subject");
        tn.setProperty("property");
        tn.setTspeak("tspeak");
        System.out.println("Hello Truenumbers!");
        System.out.println(tn);

        TnApiExamples tnApiExamples = new TnApiExamples(numberspace);

        tnApiExamples.testTnql();
        tnApiExamples.testCreateTruenumbersFromTruespeak();
        tnApiExamples.testCreateTruenumbers();

        Truenumber createdTruenumber = tnApiExamples.testCreateTruenumber();
        tnApiExamples.testTagTruenumberById(createdTruenumber.getId());
        Truenumber taggedTruenumber =  tnApiExamples.testGetTruenumberById(createdTruenumber.getId());
        System.out.println("TN ids are the same " + taggedTruenumber.getId().equals(createdTruenumber.getId()) + " " + createdTruenumber.getId());
        System.out.println("Tn tags length should be 2 " + taggedTruenumber.getTags().size());

//        tnApiExamples.testDeleteTruenumberById(taggedTruenumber.getId());

        tnApiExamples.testDeleteTruenumbers("building has length");

        tnApiExamples.testGetNumberspaces();

        TriggerApiExamples triggerApiExamples = new TriggerApiExamples(numberspace);
        var createdTriggerDefinition = triggerApiExamples.testCreateTriggerDefinition();

        System.out.println("Created trigger id " + createdTriggerDefinition.getId());

        triggerApiExamples.testGetTriggerDefinitions();

        triggerApiExamples.testDeactivateTrigger(createdTriggerDefinition.getId());

        // create kafka trigger
        var kafkaTriggerDefinition = triggerApiExamples.testCreateTriggerDefinition();


        // subscribe to changes in trigger

        var kafkaConsumer = buildKafkaConsumer();
        System.out.println(kafkaTriggerDefinition.getDestinations().get(0).getKafkaTopic());
        kafkaConsumer.subscribe(Collections.singleton(kafkaTriggerDefinition.getDestinations().get(0).getKafkaTopic()));
        final Duration pollTimeout = Duration.ofMillis(100);

        try {
            tnApiExamples.testCreateTruenumber();
            Integer triggerCount = 0;
            while (triggerCount == 0) {
                final ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(pollTimeout);
                for (final ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    KafkaTriggerMessage message = KafkaTriggerMessage.fromKafkaConsumerRecord(consumerRecord);
                    System.out.println("Message " + message.getId() + " " + message.getTruenumbers().get(0).toString() + " " + message.getTruenumbers().get(0).getId());
                    triggerCount++;
                }
            }
        } catch (WakeupException e) {
            System.out.println(e);
        }
        // create truenumbers that match trigger via kafka
        // observe trigger changes
        // create truenumbers that match trigger via api
        // observe trigger changes

        KafkaProducer producer = buildKafkaProducer();

        TruenumbersKafkaProducer tnProducer = new TruenumbersKafkaProducer(producer,
                "CREATE_TRUENUMBERS_TOPIC",
                "TAG_TRUENUMBERS_TOPIC",
                "UNTAG_TRUENUMBERS_TOPIC");



        System.out.println(kafkaTriggerDefinition.getDestinations().get(0).getKafkaTopic());
        kafkaConsumer.subscribe(Collections.singleton(kafkaTriggerDefinition.getDestinations().get(0).getKafkaTopic()));

        try {
            tnProducer.batchCreateTruenumbers(BatchCreateTruenumbersMessage.builder()
                    .numberspace(numberspace)
                    .skipStore(false)
                    .truenumbers(List.of(CreateTruenumberPayload.builder()
                            .subject("building")
                            .property("length")
                            .value("3 m")
                            .build()))
                    .build());
            Integer triggerCount = 0;
            while (triggerCount == 0) {
                final ConsumerRecords<String, String> records = kafkaConsumer.poll(pollTimeout);
                for (final ConsumerRecord<String, String> consumerRecord : records) {
                    System.out.println("Got record from kafka create");
                    KafkaTriggerMessage message = KafkaTriggerMessage.fromKafkaConsumerRecord(consumerRecord);
                    System.out.println("Message " + message.getId() + " " + message.getTruenumbers().get(0).toString() + " " + message.getTruenumbers().get(0).getId());
                    triggerCount++;
                }
            }
        } catch (WakeupException e) {
            System.out.println(e);
        } finally {
            kafkaConsumer.close();
            System.out.println("Clean up");
            triggerApiExamples.testDeactivateTrigger(kafkaTriggerDefinition.getId());
        }
        triggerApiExamples.testDeactivateTrigger(kafkaTriggerDefinition.getId());

    }
}
