package com.truenumbers.library.triggerapi.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class CreateTriggerPayload {
    protected String numberspace;
    protected String name;
    protected String description;
    protected String tnql;
    protected List<TriggerDestinationConfig> destinations;
    protected List<TriggerExecutionType> executeOn;
}
