package com.carlospatinos.stockservice.steps;

import org.springframework.boot.test.context.SpringBootTest;

import com.carlospatinos.stockservice.StockServiceApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = StockServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberConfiguration {

}
