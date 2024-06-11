package com.truenumbers.truenumbersapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Truenumber {
    @JsonProperty("_id")
    protected String id;
    protected String subject;
    protected String property;
    protected String trueStatement;
    protected List<String> tags;
    protected TruenumberValue value;
    protected Map<String, String> agent;
    protected Map<String, Map<String, Object>> tagMeta;

    @Override
    public String toString() {
        return trueStatement;
    }
}
