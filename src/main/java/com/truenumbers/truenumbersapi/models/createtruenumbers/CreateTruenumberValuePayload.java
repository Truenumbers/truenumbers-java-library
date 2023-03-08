package com.truenumbers.truenumbersapi.models.createtruenumbers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.truenumbers.truenumbersapi.TruenumberValueType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@JsonSerialize
public class CreateTruenumberValuePayload {
    protected String value;
    protected TruenumberValueType type;
}
