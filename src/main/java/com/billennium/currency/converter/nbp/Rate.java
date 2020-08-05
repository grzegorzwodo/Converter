package com.billennium.currency.converter.nbp;

import lombok.Data;

@Data
public class Rate {
    private String currency;
    private String code;
    private double mid;
}
