package com.anhdungpham.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchangeEntity, Long> {
    CurrencyExchangeEntity findAllByFromAndTo(String to, String from);
}
