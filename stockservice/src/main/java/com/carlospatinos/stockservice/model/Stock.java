package com.carlospatinos.stockservice.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
public class Stock implements Serializable {
    @lombok.NonNull
    private Integer id;
    @lombok.NonNull
    private String name;
    @lombok.NonNull
    private Double price;
    @lombok.NonNull
    private Integer availableUnits;

    private String description;
    private String imageUrl;
    private Integer amount;
}
