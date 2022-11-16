package org.truenumbers.truenumbersapi.models.createtruenumbers;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class CreateManyTruenumbersOptions {
    protected List<String> tags;
    protected Boolean skipStore;
    protected Boolean noReturn;
}
