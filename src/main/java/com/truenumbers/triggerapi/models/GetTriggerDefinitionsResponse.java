package com.truenumbers.triggerapi.models;

import lombok.Getter;

import java.util.List;

public class GetTriggerDefinitionsResponse {
    @Getter
    protected List<TriggerDefinition> triggerDefinitions;
}
