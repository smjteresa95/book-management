package com.example.bookmanagement.domain.entity;

import com.example.bookmanagement.config.LoanPolicyConfig;
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
    @Column(nullable = false)
    private LocalDateTime loanDate;
    @Column(nullable = false)
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;

    @Version
    private Long version;

}
