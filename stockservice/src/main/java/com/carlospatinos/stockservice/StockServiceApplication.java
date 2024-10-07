package com.carlospatinos.stockservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockServiceApplication.class, args);
	}

	// @Bean
	// public ApplicationRunner runner(KafkaTemplate<String, String> template) {
	// return args -> {
	// template.send("stocktopic", "footest3");
	// };
	// }
}
