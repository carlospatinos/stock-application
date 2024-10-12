package com.carlospatinos.paymentservice.config;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CircuitBreakerConfig {
    @Bean
    public CircuitBreakerConfigCustomizer externalServiceFooCircuitBreakerConfig() {
        log.info("notificationServiceCircuitBreaker running.");
        return CircuitBreakerConfigCustomizer
                .of("notificationServiceCircuitBreaker",
                        builder -> builder.slidingWindowSize(10)
                                .slidingWindowType(COUNT_BASED)
                                .waitDurationInOpenState(Duration.ofSeconds(5))
                                .minimumNumberOfCalls(5)
                                .failureRateThreshold(50.0f));
    }
}
