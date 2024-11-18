package store.order.repository.orderLine;

import org.springframework.data.jpa.repository.JpaRepository;
import store.order.model.orderline.OrderLine;

public interface OrderLineRepository  extends JpaRepository<OrderLine, Integer> {
}
