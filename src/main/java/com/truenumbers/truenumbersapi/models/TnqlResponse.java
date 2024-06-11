package com.truenumbers.truenumbersapi.models;

import com.truenumbers.truenumbersapi.Truenumber;
import lombok.Getter;

import java.util.List;

@Getter
public class TnqlResponse {
    protected List<Truenumber> truenumbers;

    protected Integer count;

    protected LimitOffset limitOffset;
}
