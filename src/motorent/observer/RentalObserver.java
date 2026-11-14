package motorent.observer;

import motorent.factory.Motorcycle;
import motorent.model.Customer;

public interface RentalObserver {

    enum Event {
        RENTED, RETURNED
    }

    void update(Event event, Customer customer, Motorcycle motorcycle);
}
