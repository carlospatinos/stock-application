package com.carlospatinos.paymentservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PaymentServiceController {

    @GetMapping("/health")
    public ResponseEntity<String> listStock() {
        log.info("Service working");
        return ResponseEntity.ok("Service working");
    }

}
