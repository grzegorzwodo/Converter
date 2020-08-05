package com.billennium.currency.converter.converter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConvertedCurrencyDto {
    double convertedValue;
    String currency;
}
