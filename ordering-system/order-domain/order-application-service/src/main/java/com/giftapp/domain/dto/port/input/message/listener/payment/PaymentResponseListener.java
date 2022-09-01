package com.giftapp.domain.dto.port.input.message.listener.payment;

public interface PaymentResponseListener {
    void peymentComplited(Object response);
    void paymentCanceled(Object response);
}
