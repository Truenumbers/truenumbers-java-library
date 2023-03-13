package com.truenumbers.triggerapi.models;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTriggerPayload {
    protected String numberspace;
    protected String name;
    protected String description;
    protected String tnql;
    protected List<TriggerDestinationConfig> destinations;
    protected List<TriggerExecutionType> executeOn;
}
