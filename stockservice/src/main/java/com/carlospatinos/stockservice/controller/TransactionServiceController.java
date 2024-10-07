package com.carlospatinos.stockservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carlospatinos.stockservice.kafka.KafkaSender;
import com.carlospatinos.stockservice.model.StockHolding;
import com.carlospatinos.stockservice.model.UserTransaction;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TransactionServiceController {
    private Map<String, StockHolding> holdingStocks;

    @Autowired
    private KafkaSender sender;

    @Value(value = "${application.topic}")
    private String appTopic;

    @PostConstruct
    public void initilizeStocks() {
        // TODO replace this for an actual storage mechanism
        holdingStocks = new HashMap<>();
        holdingStocks.put("luis@mail.com", new StockHolding("FAMSA", 10));
    }

    @GetMapping("/transactions")
    public ResponseEntity<Map<String, StockHolding>> listTransaction() {
        log.info("Returning all transactions");
        return ResponseEntity.ok(holdingStocks);
    }

    @GetMapping("/transactions/{username}")
    public ResponseEntity<StockHolding> getSpecificTransactionByUser(@PathVariable String username) {
        log.info("Obtaining transaction for user: {}", username);
        StockHolding element = holdingStocks.get(username);

        return ResponseEntity.ok(element);
    }

    @PostMapping("/transactions")
    public ResponseEntity<UserTransaction> addTransaction(@RequestBody UserTransaction transaction) {
        log.info("Creating a new transaction [{}]", transaction);

        // UserTransaction savedTransaction = userTransactionList.stream()
        // .filter(record -> transaction.getUsername().equals(record.getUsername()))
        // .findFirst().orElse(null);
        holdingStocks.put(transaction.getUserName(),
                new StockHolding(transaction.getStockName(), transaction.getAmount()));
        // sender.sendMessage("transaction-created", appTopic);
        sender.sendMessage(transaction, appTopic);

        return ResponseEntity.ok(transaction);
    }
}
