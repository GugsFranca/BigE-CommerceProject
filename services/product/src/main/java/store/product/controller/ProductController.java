package store.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.product.model.product.ProductPurchaseRequest;
import store.product.model.product.ProductPurchaseResponse;
import store.product.model.product.ProductRequest;
import store.product.model.product.ProductResponse;
import store.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest request){
        return ResponseEntity.ok(service.createProduct(request));
    }
    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> request){
        return ResponseEntity.ok(service.purchaseProducts(request));
    }
    @GetMapping("/{product_id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Integer product_id){
        return ResponseEntity.ok(service.findById(product_id));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
