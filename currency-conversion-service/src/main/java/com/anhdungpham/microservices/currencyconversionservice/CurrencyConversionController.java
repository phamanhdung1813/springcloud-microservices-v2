package com.anhdungpham.microservices.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping(path = "/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionEntity retrieveCurrencyConversion(
            @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity
    ) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionEntity> response = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionEntity.class, uriVariables);

        CurrencyConversionEntity currentConversionEntity = response.getBody();

        return new CurrencyConversionEntity(
                currentConversionEntity.getId(),
                from,
                to,
                quantity,
                currentConversionEntity.getConversionMultiple(),
                quantity.multiply(currentConversionEntity.getConversionMultiple()),
                currentConversionEntity.getEnvironment()
        );
    }

    @GetMapping(path = "/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionEntity retrieveCurrencyConversionFeign(
            @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity
    ) {

        CurrencyConversionEntity currentConversionEntity = currencyExchangeProxy.getExchangeValue(from, to);

        return new CurrencyConversionEntity(
                currentConversionEntity.getId(),
                from,
                to,
                quantity,
                currentConversionEntity.getConversionMultiple(),
                quantity.multiply(currentConversionEntity.getConversionMultiple()),
                currentConversionEntity.getEnvironment()
        );
    }

}
