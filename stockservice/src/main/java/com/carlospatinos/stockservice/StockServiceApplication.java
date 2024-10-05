package com.carlospatinos.stockservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
// @SpringBootApplication(exclude = { KafkaAutoConfiguration.class,
// TopicInitializer.class })
@EnableKafka
public class StockServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockServiceApplication.class, args);
	}

	// @Bean
	// public ApplicationRunner runner(KafkaTemplate<String, String> template) {
	// return args -> {
	// template.send("stocktopic", "footest2");
	// };
	// }

}
