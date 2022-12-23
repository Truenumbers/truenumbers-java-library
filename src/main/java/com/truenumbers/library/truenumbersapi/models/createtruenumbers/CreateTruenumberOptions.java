package com.truenumbers.library.truenumbersapi.models.createtruenumbers;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateTruenumberOptions {
    protected Boolean noReturn;
    protected Boolean skipStore;
}
