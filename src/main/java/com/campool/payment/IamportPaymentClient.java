package com.campool.payment;

import com.campool.model.PaymentInfo;
import com.campool.model.ValidatePaymentRequest;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.Payment;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IamportPaymentClient implements PaymentClient {

    @Value("${campool.iamport.api.key}")
    private String apiKey;

    @Value("${campool.iamport.api.secret}")
    private String apiSecret;

    @Override
    public PaymentInfo getPaymentById(ValidatePaymentRequest request) {
        try {
            Payment payment = new IamportClient(apiKey, apiSecret)
                    .paymentByImpUid(request.getImpUid()).getResponse();

            return new PaymentInfo(payment.getImpUid(), Long.parseLong(payment.getMerchantUid()),
                    payment.getAmount().intValue());
        } catch (IamportResponseException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
