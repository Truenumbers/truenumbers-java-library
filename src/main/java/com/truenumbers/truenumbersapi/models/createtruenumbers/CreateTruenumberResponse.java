package com.truenumbers.truenumbersapi.models.createtruenumbers;

import com.truenumbers.truenumbersapi.Truenumber;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateTruenumberResponse {
    protected List<Truenumber> truenumbers;

    public Truenumber getTruenumber() {
        return truenumbers.get(0);
    }
}
