package com.example.bookmanagement.enums;

public enum BookStatus {
    LOANED,   // 대출중 0
    AVAILABLE, // 대출가능 1
    RESERVED, // 예약중 2
    REPAIR_REQUIRED, // 수리필요 3
    LOST      // 분실 4
}