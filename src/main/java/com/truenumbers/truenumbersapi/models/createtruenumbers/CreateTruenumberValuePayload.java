package com.truenumbers.truenumbersapi.models.createtruenumbers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.truenumbers.truenumbersapi.TruenumberValueType;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class CreateTruenumberValuePayload {
    protected String value;
    protected TruenumberValueType type;
}
