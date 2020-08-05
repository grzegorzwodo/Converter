package com.billennium.currency.converter.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ConverterController {

    private final  ConverterService converterService;

    @GetMapping("convert" )
    public ConvertedCurrencyDto convert(@RequestParam String currency,@RequestParam String targetCurrency,@RequestParam double value ){
        return converterService.convert(currency.toUpperCase(),targetCurrency.toUpperCase(),value);
    }
}
