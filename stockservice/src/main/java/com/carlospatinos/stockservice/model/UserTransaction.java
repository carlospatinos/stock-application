package com.carlospatinos.stockservice.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
public class UserTransaction implements Serializable {
    @lombok.NonNull
    private String userName;
    private String stockName;
    private int amount;
    private double price;

}
