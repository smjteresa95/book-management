package com.example.bookmanagement.config;

import com.example.bookmanagement.domain.repository.LoanRecordRepository;
import com.example.bookmanagement.service.LoanRecordService;
import com.example.bookmanagement.util.mapper.LoanRecordMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    private final LoanRecordRepository repository;
    private final LoanRecordMapper mapper;
    public TestConfig(LoanRecordRepository repository, LoanRecordMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }
    @Bean
    public LoanRecordService loanRecordService(){
        return new LoanRecordService(repository, mapper);
    }
}
