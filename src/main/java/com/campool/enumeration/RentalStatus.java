package com.campool.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RentalStatus {
    TRADEABLE("거래 가능"),
    TRADING("거래 중"),
    RENTED("대여 중"),
    TRADE_COMPLETED("거래 완료");
    private final String message;
}
