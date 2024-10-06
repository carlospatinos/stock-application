package com.carlospatinos.stockservice.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carlospatinos.stockservice.model.Stock;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class StockServiceController {
    private List<Stock> stockList;

    @PostConstruct
    public void initilizeStocks() {
        // TODO replace this for an actual storage mechanism (mongodb?)
        stockList = Collections.synchronizedList(new ArrayList<>());
        stockList.add(new Stock("SSTK", Double.valueOf(22.50), 100));
        stockList.add(new Stock("NASDAG", Double.valueOf(12.80), 100));
        stockList.add(new Stock("ERIC", Double.valueOf(20.40), 100));
        stockList.add(new Stock("ALPHABET", Double.valueOf(45.30), 100));
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> listStock() {
        log.info("Returning all stocks");
        return ResponseEntity.ok(stockList);
    }

    @GetMapping("/stocks/{name}")
    public ResponseEntity<Stock> getSpecificStock(@PathVariable String name) {
        log.info("Obtaining stock with name: {}", name);
        Stock element = stockList.stream().filter(stock -> name.equals(stock.getName())).findFirst().orElse(null);
        return ResponseEntity.ok(element);
    }

    @PostMapping("/stocks")
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock) {
        log.info("Creating a new stock [{}]", stock);
        stockList.add(stock);
        return ResponseEntity.ok(stock);
    }

    @GetMapping("/version")
    public String getVersion() {
        return "1.0";
    }
}
