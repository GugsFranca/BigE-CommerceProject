package store.notification.kafka.order;

import java.math.BigDecimal;

public record Product(
        Integer id,
        String name,
        BigDecimal price,
        double quantity
) {
}
