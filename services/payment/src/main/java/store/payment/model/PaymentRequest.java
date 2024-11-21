package store.payment.model;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Payment}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public record PaymentRequest
        (Integer id,
         BigDecimal amount,
         PaymentMethod paymentMethod,
         Integer orderId,
         String orderReference,
         Customer customer) implements Serializable {
}