package com.truenumbers.truenumbersapi;

import lombok.Getter;

import java.util.List;

@Getter
public class TruenumberValue {
    protected TruenumberValueType type;
    protected String html;
    protected String value;

    protected Double magnitude;
    protected Integer tolerance;
    protected List<UnitPower> unitPowers;
    protected String quantity;

    protected TruenumberSiValue siValue;

    public boolean hasSiValue () {
        return this.type == TruenumberValueType.numeric;
    }
}
