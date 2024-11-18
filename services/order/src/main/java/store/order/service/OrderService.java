package store.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.order.exception.BusinessException;
import store.order.model.customer.CustomerClient;
import store.order.model.order.OrderRequest;
import store.order.model.orderline.OrderLineRequest;
import store.order.model.product.ProductClient;
import store.order.model.product.PurchaseRequest;
import store.order.repository.OrderRepository;
import store.order.service.orderLine.OrderLineService;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductClient productClient;
    private final CustomerClient customerClient;

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(OrderRequest request) {
        // check the customer --> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId()).orElseThrow(() -> new BusinessException("Cannot create order :: No Customer exist whit the provided id"));
        // purchase the products --> product-ms (RestTemplate)(can be done with OpenFeign as well)

        this.productClient.purchaseProducts(request.products());

        //persist order
        var order = this.repository.save(mapper.toOrder(request));

        // persist order lines
        for(PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine( new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity()));

        }

        // todo start payment process

        //send the order confirmation --> notification-ms (kafka)
        return null;
    }
}
