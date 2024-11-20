package store.order.repository.orderLine;

import org.springframework.data.jpa.repository.JpaRepository;
import store.order.model.orderline.OrderLine;
import store.order.model.orderline.OrderLineResponse;

import java.util.List;

public interface OrderLineRepository  extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
