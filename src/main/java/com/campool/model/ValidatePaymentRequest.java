package com.campool.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class ValidatePaymentRequest {

    @NotBlank(message = "거래 번호를 입력해주세요.")
    private final String impUid;

    @NonNull
    @Min(0)
    private final long merchantUid;

    @NonNull
    @Min(0)
    private final int paidAmount;

}
