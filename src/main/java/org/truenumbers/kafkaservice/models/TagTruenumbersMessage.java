package org.truenumbers.kafkaservice.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class TagTruenumbersMessage {
    protected String numberspace;
    protected String tnql;
    protected List<String> tags;
}
