package com.truenumbers.library.triggerapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TriggerDestinationConfig {
    protected TriggerDestinationType type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String kafkaTopic;

    @JsonCreator
    public TriggerDestinationConfig(
            @JsonProperty("type") TriggerDestinationType type,
            @JsonProperty("kafkaTopic") String kafkaTopic
    ) {
        this.type = type;
        this.kafkaTopic = kafkaTopic;
    }
}
