package com.truenumbers.truenumbersapi.models.createtruenumbers;

import com.truenumbers.truenumbersapi.Truenumber;
import lombok.Getter;

import java.util.List;

public class CreateManyTruenumbersResponse {
    @Getter
    protected List<Truenumber> truenumbers;
}
