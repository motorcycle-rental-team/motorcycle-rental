package motorent;

import motorent.builder.RentalRequest;
import motorent.builder.RentalRequestBuilder;
import motorent.facade.RentalFacade;
import motorent.factory.MotorcycleFactory;
import motorent.model.Customer;
import motorent.model.Extra;
import motorent.model.MotorcycleType;
import motorent.model.PricingPlan;
import motorent.observer.CustomerNotifier;
import motorent.observer.RentalEventPublisher;
import motorent.observer.StaffNotifier;

import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        MotorcycleFactory factory = new MotorcycleFactory();
        RentalEventPublisher events = new RentalEventPublisher();
        events.subscribe(new CustomerNotifier());
        events.subscribe(new StaffNotifier());

        RentalFacade rentals = new RentalFacade(List.of(
                factory.create(MotorcycleType.SPORT, "SP-001"),
                factory.create(MotorcycleType.CRUISER, "CR-001"),
                factory.create(MotorcycleType.SCOOTER, "SC-001")
        ), events);

        RentalRequest request = new RentalRequestBuilder()
                .customer(new Customer("Dana", "dana@example.com"))
                .motorcycleType(MotorcycleType.CRUISER)
                .hours(48)
                .plan(PricingPlan.DAILY)
                .withExtra(Extra.HELMET)
                .withExtra(Extra.INSURANCE)
                .withExtra(Extra.GPS)
                .build();

        RentalFacade.Receipt receipt = rentals.rent(request);
        System.out.printf(Locale.ROOT, "%s%nTotal: %.2f%n", receipt.description(), receipt.total());
        rentals.returnMotorcycle(receipt.plate());
    }
}
