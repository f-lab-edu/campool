package com.campool.enumeration

enum class RentalStatus(val message: String) {
    TRADEABLE("거래 가능"),
    TRADING("거래 중"),
    RENTED("대여 중"),
    TRADE_COMPLETED("거래 완료");
}