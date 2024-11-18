package store.order.service.orderLine;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.order.model.orderline.OrderLineRequest;
import store.order.repository.orderLine.OrderLineRepository;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;
    public Integer saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        return repository.save(order).getId();

    }
}
