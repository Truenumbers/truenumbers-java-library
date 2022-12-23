package com.truenumbers.library.truenumbersapi.models.createtruenumbers;

import lombok.Getter;
import com.truenumbers.library.truenumbersapi.Truenumber;

import java.util.List;

@Getter
public class CreateTruenumberResponse {
    protected List<Truenumber> truenumbers;

    public Truenumber getTruenumber() {
        return truenumbers.get(0);
    }
}
