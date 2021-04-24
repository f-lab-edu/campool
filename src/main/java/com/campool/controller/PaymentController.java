package com.campool.controller;

import com.campool.model.ValidatePaymentRequest;
import com.campool.service.PaymentService;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PaymentController {

    @NonNull
    private final PaymentService paymentService;

    @GetMapping("/payments/validate")
    public void payTest(@Valid ValidatePaymentRequest validatePaymentRequest) {
        paymentService.validatePayment(validatePaymentRequest);
    }

}
