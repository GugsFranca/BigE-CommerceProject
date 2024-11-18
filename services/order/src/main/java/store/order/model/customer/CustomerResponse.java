package store.order.model.customer;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
