package com.truenumbers.truenumbersapi.models.createtruenumbers;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateManyTruenumbersOptions {
    protected List<String> tags;
    protected Boolean skipStore;
    protected Boolean noReturn;
}
