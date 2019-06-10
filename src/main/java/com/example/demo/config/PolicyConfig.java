package com.example.demo.config;

import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class PolicyConfig {

    @Value("${datacenter-name-dc1}")
    private String datacenterNameDC1;

    @Value("${datacenter-name-dc2}")
    private String datacenterNameDC2;

    @Profile("DC1")
    @Bean(name = "policy")
    DCAwareRoundRobinPolicy dcAwareRoundRobinPolicyDC1() {
        return DCAwareRoundRobinPolicy.builder()
                .withLocalDc(this.datacenterNameDC1)
                .build();
    }

    @Profile("DC2")
    @Bean(name = "policy")
    DCAwareRoundRobinPolicy dcAwareRoundRobinPolicyDC2() {
        return DCAwareRoundRobinPolicy.builder()
                .withLocalDc(this.datacenterNameDC2)
                .build();
    }
}
