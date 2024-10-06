package com.carlospatinos.stockservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {
		"listeners=PLAINTEXT://localhost:9092", "port=9092" })
class StockServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
