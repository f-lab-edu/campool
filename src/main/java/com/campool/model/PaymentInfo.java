package com.campool.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class PaymentInfo {

    private final String tradeId;

    private final long merchantUid;

    private final int paidAmount;

}
