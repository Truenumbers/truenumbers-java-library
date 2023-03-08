package com.truenumbers.truenumbersapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DistinctTaxonomyItem {
    protected String srd;
    protected Integer truenumberCount;
}
