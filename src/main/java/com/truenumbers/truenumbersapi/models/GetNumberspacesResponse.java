package com.truenumbers.truenumbersapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetNumberspacesResponse {
    protected List<String> numberspaces;
}
