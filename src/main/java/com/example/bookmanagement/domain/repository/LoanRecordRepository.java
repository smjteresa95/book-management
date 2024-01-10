package com.example.bookmanagement.domain.repository;

import com.example.bookmanagement.domain.entity.LoanRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRecordRepository extends JpaRepository<LoanRecord, Long> {
    List<LoanRecord> getAllByBookId(long id);
    List<LoanRecord> getAllByUserId(long id);

}
