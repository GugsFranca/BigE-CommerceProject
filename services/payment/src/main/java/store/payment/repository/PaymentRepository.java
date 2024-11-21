package store.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.payment.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
