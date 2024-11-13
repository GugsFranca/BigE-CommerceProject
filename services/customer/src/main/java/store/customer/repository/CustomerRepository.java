package store.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import store.customer.domain.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
