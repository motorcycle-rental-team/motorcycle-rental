package motorent;

import motorent.builder.RentalRequest;
import motorent.builder.RentalRequestBuilder;
import motorent.decorator.BasicRental;
import motorent.decorator.Rental;
import motorent.decorator.RentalExtras;
import motorent.factory.Motorcycle;
import motorent.factory.MotorcycleFactory;
import motorent.model.Customer;
import motorent.model.Extra;
import motorent.model.MotorcycleType;
import motorent.model.PricingPlan;
import motorent.pricing.PricingStrategyFactory;

import java.util.Locale;

public class PricingDecoratorDemo {

    public static void main(String[] args) {
        MotorcycleFactory motorcycleFactory = new MotorcycleFactory();
        PricingStrategyFactory pricingFactory = new PricingStrategyFactory();
        RentalExtras rentalExtras = new RentalExtras();
        Customer customer = new Customer("Aslan", "aslan@mail.kz");

        System.out.println("=== Strategy ===");
        for (PricingPlan plan : PricingPlan.values()) {
            int hours = switch (plan) {
                case HOURLY -> 5;
                case DAILY -> 48;
                case WEEKLY -> 168;
            };
            RentalRequest request = new RentalRequestBuilder()
                    .customer(customer)
                    .motorcycleType(MotorcycleType.SPORT)
                    .hours(hours)
                    .plan(plan)
                    .build();
            Motorcycle motorcycle = motorcycleFactory.create(request.type(), "SP-001");
            Rental rental = new BasicRental(motorcycle, request.hours(),
                    pricingFactory.create(request.plan()));
            System.out.printf(Locale.ROOT, "%s | %s | Cost: %.2f%n",
                    request.plan(), rental.description(), rental.cost());
        }

        System.out.println("=== Decorator ===");
        RentalRequest request = new RentalRequestBuilder()
                .customer(customer)
                .motorcycleType(MotorcycleType.CRUISER)
                .hours(48)
                .plan(PricingPlan.DAILY)
                .withExtra(Extra.HELMET)
                .withExtra(Extra.INSURANCE)
                .withExtra(Extra.GPS)
                .build();
        Motorcycle motorcycle = motorcycleFactory.create(request.type(), "CR-001");
        Rental rental = new BasicRental(motorcycle, request.hours(),
                pricingFactory.create(request.plan()));
        System.out.printf(Locale.ROOT, "%s | Cost: %.2f%n", rental.description(), rental.cost());
        rental = rentalExtras.apply(rental, request.extras());
        System.out.printf(Locale.ROOT, "%s | Total: %.2f%n", rental.description(), rental.cost());
    }
}
