package com.truenumbers.examples;

import com.truenumbers.triggerapi.TriggerApi;
import com.truenumbers.triggerapi.models.*;
import com.truenumbers.utils.TnApiException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class TriggerApiExamples {
    protected String numberspace = "_system:numberspace/test-nard-parser";
    protected TriggerApi triggerApi = new TriggerApi("http://gov.truenumbers.com:8082");

    public TriggerApiExamples () {

    }

    public TriggerApiExamples (String numberspace) {
        this();
        this.numberspace = numberspace;
    }

    public TriggerApiExamples(String numberspace, String tnApiBase) {
        this(numberspace);
        this.triggerApi = new TriggerApi(tnApiBase);
    }

    public TriggerDefinition testCreateTriggerDefinition () throws TnApiException, IOException, URISyntaxException, InterruptedException {
        var payload = CreateTriggerPayload.builder()
                .numberspace(numberspace)
                .name("java-library-test")
                .tnql("* has *")
                .description("Testing from java library")
                .destinations(List.of(TriggerDestinationConfig.builder()
                        .type(TriggerDestinationType.KAFKA)
                        .kafkaTopic("test-trigger-from-lib")
                        .build()
                ))
                .executeOn(List.of(TriggerExecutionType.CREATE))
                .build();
        var response = triggerApi.createTriggerDefinition(payload);
        return response.getTriggerDefinition();
    }

    public void testGetTriggerDefinitions () throws TnApiException, IOException, URISyntaxException, InterruptedException {
        var response = triggerApi.getTriggerDefinitions(TriggerStatus.ACTIVE, numberspace);
        System.out.println("Triggers returned count " + response.getTriggerDefinitions().size());
    }

    public void testDeactivateTrigger (String id) throws TnApiException, URISyntaxException, IOException, InterruptedException {
        triggerApi.deactivateTriggerDefinition(id);
    }
}
