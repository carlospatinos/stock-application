package com.carlospatinos.stockservice.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
    // Besides Map was a better option for efficiency, List was used to demonstrate
    // the use of streams.
    private List<UserTransaction> userTransactionList;

    @Autowired
    private KafkaSender sender;

    @Value(value = "${application.topic}")
    private String appTopic;

    @PostConstruct
    public void initilizeStocks() {
        // TODO replace this for an actual storage mechanism
        userTransactionList = Collections.synchronizedList(new ArrayList<>());

        UserTransaction transaction = new UserTransaction("luis@mail.com");
        Map<String, StockHolding> stocks = new HashMap<>();
        stocks.put("FAMSA", new StockHolding("FAMSA", 10));
        transaction.setHoldingStocks(stocks);
        userTransactionList.add(transaction);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<UserTransaction>> listTransaction() {
        log.info("Returning all transactions");
        sender.sendMessage("Test", appTopic);
        return ResponseEntity.ok(userTransactionList);
    }

    @GetMapping("/transactions/{username}")
    public ResponseEntity<UserTransaction> getSpecificTransactionByUser(@PathVariable String username) {
        log.info("Obtaining transaction for user: {}", username);
        UserTransaction element = userTransactionList.stream()
                .filter(transaction -> username.equals(transaction.getUsername())).findFirst()
                .orElse(null);
        return ResponseEntity.ok(element);
    }

    @PostMapping("/transactions")
    public ResponseEntity<UserTransaction> addTransaction(@RequestBody UserTransaction transaction) {
        log.info("Creating a new transaction [{}]", transaction);

        // UserTransaction savedTransaction = userTransactionList.stream()
        // .filter(record -> transaction.getUsername().equals(record.getUsername()))
        // .findFirst().orElse(null);

        userTransactionList.add(transaction);
        return ResponseEntity.ok(transaction);
    }
}
