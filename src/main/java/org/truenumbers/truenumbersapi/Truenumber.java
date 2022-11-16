package org.truenumbers.truenumbersapi;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Truenumber {
    @JsonProperty("_id")
    protected String id;
    protected String subject;
    protected String property;
    protected String tspeak;
    protected List<TruenumberTag> tags;
    protected TruenumberValue value;
    protected Map<String, String> agent;

    @Override
    public String toString() {
        return tspeak;
    }
}
