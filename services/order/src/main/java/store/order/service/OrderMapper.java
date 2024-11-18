package store.order.service;

import org.springframework.stereotype.Service;
import store.order.model.order.Order;
import store.order.model.order.OrderRequest;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }
}
