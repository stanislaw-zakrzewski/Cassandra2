package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ContactPointsConfig {

    @Value("${contact-point-dc1}")
    private String contactPointsDC1;

    @Value("${contact-point-dc2}")
    private String contactPointsDC2;

    @Profile("DC1")
    @Bean(name = "contactPoint")
    String contactPointsDC1() {
        return contactPointsDC1;
    }

    @Profile("DC2")
    @Bean(name = "contactPoint")
    String contactPointsDC2() {
        return contactPointsDC2;
    }
}
