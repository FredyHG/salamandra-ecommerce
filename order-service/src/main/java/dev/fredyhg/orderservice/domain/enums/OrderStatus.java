package dev.fredyhg.orderservice.domain.enums;

public enum OrderStatus {
    STARTED,
    PENDING,
    CANCELED,
    PAYMENT_REQUIRED,
    PAYMENT_PENDING,
    PAYMENT_SUCCESS,
    PAYMENT_FAILED
}
