package store.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.payment.model.PaymentNotificationRequest;
import store.payment.model.PaymentRequest;
import store.payment.notification.NotificationProducer;
import store.payment.repository.PaymentRepository;

@Service
@RequiredArgsConstructor
public class PaymentService {
   private final PaymentRepository repository;
   private final PaymentMapper mapper;
   private final NotificationProducer notificationProducer;
    public Integer createPayment(PaymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));
        notificationProducer.sendNotification(new PaymentNotificationRequest(
                request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().firstname(),
                request.customer().lastname(),
                request.customer().email()));

        return payment.getId();
    }
}
