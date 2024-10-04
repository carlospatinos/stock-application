package com.carlospatinos.paymentservice.model;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString(includeFieldNames = false)
public class UserTransaction implements Serializable {
    @lombok.NonNull
    private String username;
    private Map<String, StockHolding> holdingStocks;
}
