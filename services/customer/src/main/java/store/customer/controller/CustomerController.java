package store.customer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.customer.domain.CustomerRequest;
import store.customer.domain.CustomerResponse;
import store.customer.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request){
     return ResponseEntity.ok(service.createCustomer(request));
    }
    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request){
        service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok(service.findAllCustomers());
    }
    @GetMapping("/exits/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("id") String id){
        return ResponseEntity.ok(service.existById(id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(service.findById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id ){
        service.deleteCustomer(id);
        return ResponseEntity.accepted().build();
    }
}
