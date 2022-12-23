package com.truenumbers.library.truenumbersapi.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LimitOffset {
    protected Integer limit = 1000;

    protected Integer offset = 0;

    public LimitOffset(Integer limit, Integer offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public LimitOffset() {

    }
}
