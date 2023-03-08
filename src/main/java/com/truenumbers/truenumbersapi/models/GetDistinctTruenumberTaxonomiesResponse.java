package com.truenumbers.truenumbersapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDistinctTruenumberTaxonomiesResponse {
    protected Integer totalTaxonomyCount;
    protected Integer totalTruenumberCount;
    protected TaxonomyType taxonomyType;
    protected List<DistinctTaxonomyItem> taxonomy;
}
