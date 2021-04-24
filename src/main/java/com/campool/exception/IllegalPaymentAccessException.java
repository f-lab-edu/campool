package com.campool.exception;

public class IllegalPaymentAccessException extends RuntimeException {

    private static final String NECESSARY_MESSAGE = "위/변조된 결제 시도입니다.";

    public IllegalPaymentAccessException() {
        super(NECESSARY_MESSAGE);
    }

    public IllegalPaymentAccessException(String message) {
        super(message + NECESSARY_MESSAGE);
    }

}
