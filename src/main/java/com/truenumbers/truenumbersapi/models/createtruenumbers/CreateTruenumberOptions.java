package com.truenumbers.truenumbersapi.models.createtruenumbers;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTruenumberOptions {
    protected Boolean noReturn;
    protected Boolean skipStore;
}
