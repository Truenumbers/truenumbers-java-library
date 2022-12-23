package com.truenumbers.library.kafkaservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import com.truenumbers.library.truenumbersapi.models.createtruenumbers.CreateTruenumberPayload;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchCreateTruenumbersMessage {
    protected String numberspace;

    @Builder.Default
    protected String truespeak = "";

    @Builder.Default
    protected Boolean skipStore = false;

    @Builder.Default
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected List<CreateTruenumberPayload> truenumbers = new ArrayList<>();
}
