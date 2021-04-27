package com.campool.payment;

import com.campool.model.PaymentInfo;
import com.campool.model.ValidatePaymentRequest;

public interface PaymentClient {

    PaymentInfo getPaymentById(ValidatePaymentRequest request);

}
