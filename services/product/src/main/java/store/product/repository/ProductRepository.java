package store.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.product.model.product.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    List<Product> findAllByIdInOrderById(List<Integer> productIds);
}
