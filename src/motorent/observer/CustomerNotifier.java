package motorent.observer;

import motorent.factory.Motorcycle;
import motorent.model.Customer;

public final class CustomerNotifier implements RentalObserver {

    @Override
    public void update(Event event, Customer customer, Motorcycle motorcycle) {
        String message = switch (event) {
            case RENTED -> "Your rental has started: ";
            case RETURNED -> "Your return has been confirmed: ";
        };
        System.out.println("Customer [" + customer.name() + "]: " + message + motorcycle);
    }
}
