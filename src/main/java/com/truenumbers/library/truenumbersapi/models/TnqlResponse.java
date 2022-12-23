package com.truenumbers.library.truenumbersapi.models;

import lombok.Getter;
import com.truenumbers.library.truenumbersapi.Truenumber;

import java.util.List;

@Getter
public class TnqlResponse {
    protected List<Truenumber> truenumbers;

    protected Integer count;

    protected LimitOffset limitOffset;
}
