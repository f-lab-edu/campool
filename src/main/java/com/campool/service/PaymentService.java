package com.campool.service;

import com.campool.enumeration.BookingStatus;
import com.campool.exception.IllegalPaymentAccessException;
import com.campool.mapper.BookingMapper;
import com.campool.model.PaymentInfo;
import com.campool.model.ValidatePaymentRequest;
import com.campool.payment.PaymentClient;
import java.util.NoSuchElementException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    @NonNull
    private final BookingMapper bookingMapper;

    @NonNull
    private final PaymentClient paymentClient;

    public void validatePayment(ValidatePaymentRequest request) {
        PaymentInfo payment = paymentClient.getPaymentById(request);

        if (isNotValidPayment(request, payment)) {
            throw new IllegalPaymentAccessException("검증 요청의 매개 변수가 불일치합니다. ");
        }

        long bookingId = payment.getMerchantUid();
        int amountToBePaid = getAmountToBePaid(bookingId);

        if (payment.getPaidAmount() == amountToBePaid) { // 결제한 금액과 결제되어야 하는 금액이 일치 - 결제 성공
            bookingMapper.updateStatusById(bookingId, BookingStatus.PAYMENT_COMPLETED);
        } else {
            throw new IllegalPaymentAccessException("결제 금액이 불일치합니다. ");
        }
    }

    public int getAmountToBePaid(long id) {
        Integer amountToBePaid = bookingMapper
                .findAmountByIdAndStatus(id, BookingStatus.PAYMENT_PENDING);
        if (amountToBePaid == null) {
            throw new NoSuchElementException("결제가 필요한 예약 데이터가 존재하지 않습니다.");
        }
        return amountToBePaid;
    }

    public boolean isNotValidPayment(ValidatePaymentRequest request, PaymentInfo payment) {
        return !(request.getImpUid().equals(payment.getTradeId())
                && request.getMerchantUid() == payment.getMerchantUid()
                && request.getPaidAmount() == payment.getPaidAmount());
    }

    public void cancelPayment(long id) {
        paymentClient.cancelPaymentById(String.valueOf(id));
    }

}
