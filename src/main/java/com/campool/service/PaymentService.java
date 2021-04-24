package com.campool.service;

import com.campool.enumeration.BookingStatus;
import com.campool.exception.IllegalPaymentAccessException;
import com.campool.mapper.BookingMapper;
import com.campool.model.ValidatePaymentRequest;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.Payment;
import java.io.IOException;
import java.util.NoSuchElementException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    @Value("${campool.iamport.api.key}")
    private String apiKey;

    @Value("${campool.iamport.api.secret}")
    private String apiSecret;

    @NonNull
    private final BookingMapper bookingMapper;

    public void validatePayment(ValidatePaymentRequest request) {
        Payment payment = getPaymentByUid(request.getImpUid());

        if (isNotValidPayment(request, payment)) {
            throw new IllegalPaymentAccessException("검증 요청의 매개 변수가 불일치합니다. ");
        }

        long bookingId = Long.parseLong(payment.getMerchantUid());
        int amountToBePaid = getAmountToBePaid(bookingId);

        if (payment.getAmount().intValue() == amountToBePaid) { // 결제한 금액과 결제되어야 하는 금액이 일치 - 결제 성공
            bookingMapper.updateStatusById(bookingId, BookingStatus.PAYMENT_COMPLETED);
        } else {
            throw new IllegalPaymentAccessException("결제 금액이 불일치합니다. ");
        }
    }

    public Payment getPaymentByUid(String impUid) {
        try {
            return new IamportClient(apiKey, apiSecret).paymentByImpUid(impUid).getResponse();
        } catch (IamportResponseException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getAmountToBePaid(long id) {
        Integer amountToBePaid = bookingMapper
                .selectAmountByIdAndStatus(id, BookingStatus.PAYMENT_PENDING);
        if (amountToBePaid == null) {
            throw new NoSuchElementException("결제가 필요한 예약 데이터가 존재하지 않습니다.");
        }
        return amountToBePaid;
    }

    public boolean isNotValidPayment(ValidatePaymentRequest request, Payment payment) {
        return !(request.getImpUid().equals(payment.getImpUid())
                && request.getMerchantUid() == Long.parseLong(payment.getMerchantUid())
                && request.getPaidAmount() == payment.getAmount().intValue());
    }

}
