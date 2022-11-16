package org.truenumbers.kafkaservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.truenumbers.truenumbersapi.models.createtruenumbers.CreateTruenumberPayload;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchCreateTruenumbersMessage {
    protected String numberspace;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String truespeak = "";

    protected Boolean skipStore = false;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected List<CreateTruenumberPayload> truenumbers = new ArrayList<>();
}
