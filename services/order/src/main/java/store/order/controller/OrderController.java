package store.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.order.model.order.OrderRequest;
import store.order.model.order.OrderResponse;
import store.order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createOrder(request));
    }
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse>findById(@PathVariable Integer orderId){
        return ResponseEntity.ok(service.findById(orderId));
    }
 }
