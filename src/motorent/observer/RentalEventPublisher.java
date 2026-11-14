package motorent.observer;

import motorent.factory.Motorcycle;
import motorent.model.Customer;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class RentalEventPublisher {

    private final Set<RentalObserver> observers = new LinkedHashSet<>();

    public void subscribe(RentalObserver observer) {
        observers.add(Objects.requireNonNull(observer, "Observer is required"));
    }

    public void publish(RentalObserver.Event event, Customer customer, Motorcycle motorcycle) {
        for (RentalObserver observer : List.copyOf(observers)) {
            try {
                observer.update(event, customer, motorcycle);
            } catch (RuntimeException exception) {
                System.err.println("Rental notification failed: " + exception.getMessage());
            }
        }
    }
}
