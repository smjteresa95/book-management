package com.example.bookmanagement.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "loan_record")
public class LoanRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;
    private Long bookId;
    private Long userId;
    private LocalDateTime loanDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;

    @PrePersist
    @PreUpdate
    public void calculateDueDate() {
        if (loanDate != null) {
            dueDate = loanDate.plusDays(14);
        }
    }
}
