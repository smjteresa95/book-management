package com.example.bookmanagement.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "loan_record")
public class LoanRecord {
    @Id
    private Long loanId;
    private Long bookUId;
    private Long userId;
    private Timestamp loanDate;
    private Timestamp dueDate;
    private Timestamp returnDate;

}
