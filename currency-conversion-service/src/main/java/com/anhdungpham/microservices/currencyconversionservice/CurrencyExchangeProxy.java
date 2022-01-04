package com.anhdungpham.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value = "currency-exchange-service", url = "localhost:8000")
@FeignClient(value = "currency-exchange-service")
public interface CurrencyExchangeProxy {
    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionEntity getExchangeValue(@PathVariable String from, @PathVariable String to);
}
