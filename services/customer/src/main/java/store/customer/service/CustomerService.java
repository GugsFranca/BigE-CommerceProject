package store.customer.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import store.customer.domain.Customer;
import store.customer.domain.CustomerRequest;
import store.customer.domain.CustomerResponse;
import store.customer.exception.CustomerNotFoundException;
import store.customer.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;


    public String createCustomer(CustomerRequest request) {
        if (existById(request.id())){
            throw new DuplicateKeyException("Customer already has the provider ID:: " + request.id());
        }
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(String.
                        format("Cannot update Customer:: No customer found with the provider ID:: %s", request.id())));
        mergeCustomer(customer, request); //BeanUtils.copyProperties(request, customer);
        repository.save(customer);
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll().stream().map(mapper::fromCustomer).collect(Collectors.toList());
    }

    public Boolean existById(String id) {
        return repository.findById(id).isPresent();
    }

    public CustomerResponse findById(String id) {
        return repository.findById(id).map(mapper::fromCustomer).orElseThrow(
                () -> new CustomerNotFoundException(String.format("No customer found with this id :: %s", id)));
    }

    public void deleteCustomer(String id) {
        repository.deleteById(id);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())) {
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }

    }
}
