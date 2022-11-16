package org.truenumbers.triggerapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TriggerDefinition {
    protected String id;
    protected TriggerStatus status;
    protected String name;
    protected String description;
    protected String numberspace;
    protected String tnql;
    protected List<TriggerExecutionType> executeOn;

    protected List<TriggerDestinationConfig> destinations;
}
