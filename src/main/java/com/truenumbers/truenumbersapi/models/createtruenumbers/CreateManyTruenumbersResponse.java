package com.truenumbers.truenumbersapi.models.createtruenumbers;

import lombok.Getter;
import com.truenumbers.truenumbersapi.Truenumber;

import java.util.List;

public class CreateManyTruenumbersResponse {
    @Getter
    protected List<Truenumber> truenumbers;
}
