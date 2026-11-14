package motorent.observer;

import motorent.factory.Motorcycle;
import motorent.model.Customer;

public final class StaffNotifier implements RentalObserver {

    @Override
    public void update(Event event, Customer customer, Motorcycle motorcycle) {
        String status = switch (event) {
            case RENTED -> "rented by ";
            case RETURNED -> "returned by ";
        };
        System.out.println("Staff: " + motorcycle.plate() + " " + status + customer.name());
    }
}
