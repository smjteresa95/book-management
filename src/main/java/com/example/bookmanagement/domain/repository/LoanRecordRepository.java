package com.example.bookmanagement.domain.repository;

import com.example.bookmanagement.domain.entity.LoanRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRecordRepository extends JpaRepository<LoanRecord, Long> {
}
