package com.anhdungpham.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchangeEntity getExchangeValue(@PathVariable String from,
                                                   @PathVariable String to) {
//        CurrencyExchangeEntity currencyExchange = new CurrencyExchangeEntity(1001L, from, to, BigDecimal.valueOf(65));

        CurrencyExchangeEntity currencyExchange = currencyExchangeRepository.findAllByFromAndTo(from, to);

        if (currencyExchange == null) {
            throw new RuntimeException("Cannot get value" + from + to);
        }

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}
