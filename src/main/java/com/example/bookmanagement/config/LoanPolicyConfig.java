package com.example.bookmanagement.config;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "loan-policy")
@Getter @Setter
public class LoanPolicyConfig {
    private int maxBooksPerUser;
    private int loanDays;
}
