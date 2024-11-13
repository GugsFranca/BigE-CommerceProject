package store.product.model.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotBlank(message = "Product name is required")
        String name,
        @NotBlank(message = "Product description is required")
        String description,
        @Positive(message = "Available quantity should be is positive")
        double availableQuantity,
        @Positive(message = "Available quantity should be is positive")
        BigDecimal price,
        @NotNull(message = "Product category is required")
        Integer category_id
) {
}
