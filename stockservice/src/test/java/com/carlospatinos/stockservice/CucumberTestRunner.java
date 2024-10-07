package com.carlospatinos.stockservice;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.carlospatinos.stockservice.steps")
// @EmbeddedKafka(partitions = 1, brokerProperties = {
// "listeners=PLAINTEXT://localhost:9093", "port=9093" })
public class CucumberTestRunner {

}
