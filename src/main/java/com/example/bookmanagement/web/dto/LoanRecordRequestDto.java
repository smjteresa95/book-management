package com.example.bookmanagement.web.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@ToString
public class LoanRecordRequestDto {
    private Long bookId;
    private Long userId;

    @Builder.Default
    private LocalDateTime returnDate = null;
}
