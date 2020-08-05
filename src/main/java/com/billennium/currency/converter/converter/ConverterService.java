package com.billennium.currency.converter.converter;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConverterService {
    public static final String PLN = "PLN";
    public static Map<String, Double> courses;

    public ConvertedCurrencyDto convert(String currency, String targetCurrency, double value) {
        double convertedValue;
        if(currency.equalsIgnoreCase(targetCurrency)){
           return buildCurrencyDto(targetCurrency, value);
        }
        if (currency.equalsIgnoreCase(PLN)) {
            convertedValue = value / courses.get(targetCurrency);
        } else if (targetCurrency.equalsIgnoreCase(PLN)) {
            convertedValue = convertToPln(value, currency);
        } else {
            convertedValue = convertToPln(value, currency) / courses.get(targetCurrency);
        }

        return buildCurrencyDto(targetCurrency, convertedValue);
    }

    private ConvertedCurrencyDto buildCurrencyDto(String targetCurrency, double convertedValue) {
        return ConvertedCurrencyDto.builder()
                .convertedValue(round(convertedValue))
                .currency(targetCurrency).build();
    }

    private double convertToPln(double value, String currency) {
        return value * courses.get(currency);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
