package com.example.bookmanagement.web.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@ToString
public class LoanRecordResponseDto {
    private Long loanId;
    private Long bookId;
    private Long userId;
    private LocalDateTime loanDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private Long version;

}
