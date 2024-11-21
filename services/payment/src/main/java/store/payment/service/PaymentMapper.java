package store.payment.service;

import org.springframework.stereotype.Service;
import store.payment.model.Payment;
import store.payment.model.PaymentRequest;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .orderId(request.orderId())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod()).build();
    }
}
