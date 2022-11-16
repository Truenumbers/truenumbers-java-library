package org.truenumbers.truenumbersapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagTruenumbersResponse {
    protected Integer modifiedCount;
    protected Integer taggedCount;
    protected Integer tagsRemovedCount;
}
