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
public class StockHolding implements Serializable {
    @lombok.NonNull
    private String stockName;
    @lombok.NonNull
    private Integer holdingUnits;
}
