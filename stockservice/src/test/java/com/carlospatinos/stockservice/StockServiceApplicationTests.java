package com.carlospatinos.stockservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
// @SpringBootTest("spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration")
@EnableAutoConfiguration(exclude = { KafkaAutoConfiguration.class })

class StockServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
