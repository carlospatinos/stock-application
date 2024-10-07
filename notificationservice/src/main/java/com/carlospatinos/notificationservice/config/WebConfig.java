package com.carlospatinos.notificationservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebMvc
@Slf4j
public class WebConfig implements WebMvcConfigurer {
    @Value(value = "${application.allowed.domains}")
    private String allowedDomains;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("CorsConfig enabled for {}", allowedDomains);
        registry.addMapping("/**").allowedOrigins(allowedDomains);
    }
}