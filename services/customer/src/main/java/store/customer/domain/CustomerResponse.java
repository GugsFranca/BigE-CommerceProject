package store.customer.domain;

public record CustomerResponse(String id, String firstName, String lastName, String email, Address address) {
}
