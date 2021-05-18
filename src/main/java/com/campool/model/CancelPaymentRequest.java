package com.campool.model;

import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class CancelPaymentRequest {

    @NonNull
    @Min(0)
    private final long merchantUid;

    @NonNull
    @Min(0)
    private final int paidAmount;

}
