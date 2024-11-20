package store.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.order.model.orderline.OrderLineResponse;
import store.order.service.orderLine.OrderLineService;

import java.util.List;

@RestController
@RequestMapping("/order-line")
@RequiredArgsConstructor
public class OrderLineController {
    private final OrderLineService service;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderLineResponse>> findByOderId(@PathVariable Integer orderId){
        return ResponseEntity.ok(service.findAllByOrderId(orderId));
    }
}
