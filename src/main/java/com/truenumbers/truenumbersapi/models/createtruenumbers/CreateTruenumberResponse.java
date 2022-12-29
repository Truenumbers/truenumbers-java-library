package com.truenumbers.truenumbersapi.models.createtruenumbers;

import lombok.Getter;
import com.truenumbers.truenumbersapi.Truenumber;

import java.util.List;

@Getter
public class CreateTruenumberResponse {
    protected List<Truenumber> truenumbers;

    public Truenumber getTruenumber() {
        return truenumbers.get(0);
    }
}
