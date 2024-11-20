package store.order.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.order.exception.BusinessException;
import store.order.kafka.OrderProducer;
import store.order.model.customer.CustomerClient;
import store.order.model.order.OrderConfirmation;
import store.order.model.order.OrderRequest;
import store.order.model.order.OrderResponse;
import store.order.model.orderline.OrderLineRequest;
import store.order.model.product.ProductClient;
import store.order.model.product.PurchaseRequest;
import store.order.repository.OrderRepository;
import store.order.service.orderLine.OrderLineService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductClient productClient;
    private final CustomerClient customerClient;

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Integer createOrder(OrderRequest request) {
        // check the customer --> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId()).orElseThrow(() -> new BusinessException("Cannot create order :: No Customer exist whit the provided id"));
        // purchase the products --> product-ms (RestTemplate)(can be done with OpenFeign as well)

        var purchasedProduct = this.productClient.purchaseProducts(request.products());

        //persist order
        var order = this.repository.save(mapper.toOrder(request));

        // persist order lines
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity()));

        }

        // todo start payment process

        //send the order confirmation --> notification-ms (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProduct)
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll().stream().map(mapper::fromOrder).collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId).map(mapper::fromOrder).orElseThrow(
                () -> new EntityNotFoundException("No order find with the provided id :: " + orderId));
    }
}
