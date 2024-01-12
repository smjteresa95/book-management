package com.example.bookmanagement.domain.repository;

import com.example.bookmanagement.domain.entity.LoanRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRecordRepository extends JpaRepository<LoanRecord, Long> {
    List<LoanRecord> getAllByBookId(long id);
    List<LoanRecord> getAllByUserId(long id);

    @Query("SELECT lr FROM LoanRecord lr WHERE lr.bookId = :bookId AND lr.userId = :userId")
    Optional<LoanRecord> getReturnedBook(@Param("bookId") Long bookId, @Param("userId") Long userId);

    @Modifying
    @Query("UPDATE LoanRecord lr SET lr.returnDate = :returnDate WHERE lr.bookId = :bookId AND lr.userId = :userId AND lr.returnDate IS NULL")
    void updateReturnDate(@Param("returnDate") LocalDateTime returnDate, @Param("bookId") Long bookId, @Param("userId") Long userId);
}
