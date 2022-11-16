package org.truenumbers.truenumbersapi;

import lombok.Getter;

import java.util.List;

@Getter
public class TruenumberSiValue {
    protected TruenumberValueType type;
    protected String html;
    protected String value;

    protected String quantity;
    protected Double magnitude;
    protected Integer tolerance;
    protected List<UnitPower> unitPowers;
}
