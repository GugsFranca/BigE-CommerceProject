package store.customer.service;

import org.springframework.stereotype.Service;
import store.customer.domain.Customer;
import store.customer.domain.CustomerRequest;
import store.customer.domain.CustomerResponse;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        if (request == null){
            return null;
        }
        return Customer.builder().id(request.id()).firstName(request.firstName()).lastName(request.lastName())
                .email(request.email()).address(request.address()).build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getFirstName(),
                customer.getLastName(), customer.getEmail(), customer.getAddress());
    }
}
