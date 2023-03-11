package com.truenumbers.truenumbersapi.models.createtruenumbers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = CreateTruenumberPayloadSerializer.class)
public class CreateTruenumberPayload {
    protected String subject;
    protected String property;
    protected String value;
    protected CreateTruenumberValuePayload valuePayload;
    protected List<String> tags = new ArrayList<>();
}
