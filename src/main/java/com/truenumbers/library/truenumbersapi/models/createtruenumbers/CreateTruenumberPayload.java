package com.truenumbers.library.truenumbersapi.models.createtruenumbers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@JsonSerialize
public class CreateTruenumberPayload {
    protected String subject;
    protected String property;
    protected String value;
    protected List<String> tags = new ArrayList<>();
}
