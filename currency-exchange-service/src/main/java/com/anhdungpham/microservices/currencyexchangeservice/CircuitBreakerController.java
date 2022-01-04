package com.anhdungpham.microservices.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(path="sample-api")
//    @Retry(name="sample-api", fallbackMethod = "fallBackResponse")

//    @CircuitBreaker(name="sample-api", fallbackMethod = "fallBackResponse")
//    @RateLimiter(name="sample-uri")
    @Bulkhead(name="sample-api")
    public String SampleApi() {
        logger.info("SampleApi Here");
//        ResponseEntity<String> response = new RestTemplate().getForEntity(
//                "http://localhost:8080/some-url",
//                String.class
//        );
//        return response.getBody();
        return "logging sample api";
    }

    public String fallBackResponse(Exception ex) {
        return "FALL BACK !!!!";
    }
}
