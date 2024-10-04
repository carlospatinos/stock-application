package com.carlospatinos.paymentservice.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(includeFieldNames = false)
public class Stock implements Serializable {
    @lombok.NonNull
    private String name;
    @lombok.NonNull
    private Double price;
    @lombok.NonNull
    private Integer availableUnits;
}
