package motorent.model;

public record Customer(String name, String email) {

    public Customer {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Customer name must not be blank");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Customer email is invalid: " + email);
        }
    }
}
