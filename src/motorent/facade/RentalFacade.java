package motorent.facade;

import motorent.builder.RentalRequest;
import motorent.decorator.BasicRental;
import motorent.decorator.Rental;
import motorent.decorator.RentalExtras;
import motorent.factory.Motorcycle;
import motorent.model.Customer;
import motorent.observer.RentalEventPublisher;
import motorent.observer.RentalObserver.Event;
import motorent.pricing.PricingStrategy;
import motorent.pricing.PricingStrategyFactory;

import java.util.List;
import java.util.Objects;

public final class RentalFacade {

    public record Receipt(String plate, String description, double total) {
    }

    private final RentalInventory inventory;
    private final PricingStrategyFactory pricingFactory = new PricingStrategyFactory();
    private final RentalExtras rentalExtras = new RentalExtras();
    private final RentalEventPublisher events;

    public RentalFacade(List<Motorcycle> fleet, RentalEventPublisher events) {
        inventory = new RentalInventory(fleet);
        this.events = Objects.requireNonNull(events, "Event publisher is required");
    }

    public synchronized Receipt rent(RentalRequest request) {
        Objects.requireNonNull(request, "Rental request is required");
        Customer customer = Objects.requireNonNull(request.customer(), "Customer is required");
        Motorcycle motorcycle = inventory.findAvailable(request.type());
        PricingStrategy pricing = pricingFactory.create(request.plan());
        Rental rental = new BasicRental(motorcycle, request.hours(), pricing);
        rental = rentalExtras.apply(rental, request.extras());

        Receipt receipt = new Receipt(motorcycle.plate(), rental.description(), rental.cost());
        inventory.registerRental(motorcycle, customer);
        events.publish(Event.RENTED, customer, motorcycle);
        return receipt;
    }

    public synchronized void returnMotorcycle(String plate) {
        RentalInventory.ActiveRental rental = inventory.release(plate);
        events.publish(Event.RETURNED, rental.customer(), rental.motorcycle());
    }
}
