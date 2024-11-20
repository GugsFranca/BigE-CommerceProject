package store.order.service.orderLine;

import org.springframework.stereotype.Service;
import store.order.model.order.Order;
import store.order.model.orderline.OrderLine;
import store.order.model.orderline.OrderLineRequest;
import store.order.model.orderline.OrderLineResponse;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(Order
                        .builder()
                        .id(request.orderId())
                        .build())
                .productId(request.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
