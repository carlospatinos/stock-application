package com.carlospatinos.stockservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
// @SpringBootTest("spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration")
// @EnableAutoConfiguration(exclude = { KafkaAutoConfiguration.class })
@EmbeddedKafka(partitions = 1, brokerProperties = {
		"listeners=PLAINTEXT://localhost:9092", "port=9092" })
class StockServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
