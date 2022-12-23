package com.truenumbers.library.truenumbersapi.models.createtruenumbers;

import lombok.Getter;
import com.truenumbers.library.truenumbersapi.Truenumber;

import java.util.List;

public class CreateManyTruenumbersResponse {
    @Getter
    protected List<Truenumber> truenumbers;
}
