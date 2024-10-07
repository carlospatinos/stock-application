package com.carlospatinos.stockservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration"
// @EnableAutoConfiguration(exclude = { KafkaAutoConfiguration.class }) //
// @EmbeddedKafka(partitions = 1, brokerProperties = {
// "listeners=PLAINTEXT://localhost:9093", "port=9093" })
class StockServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
