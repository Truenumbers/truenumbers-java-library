package org.truenumbers.truenumbersapi.models.createtruenumbers;

import lombok.Getter;
import org.truenumbers.truenumbersapi.Truenumber;

import java.util.List;

@Getter
public class CreateTruenumberResponse {
    protected List<Truenumber> truenumbers;

    public Truenumber getTruenumber() {
        return truenumbers.get(0);
    }
}
