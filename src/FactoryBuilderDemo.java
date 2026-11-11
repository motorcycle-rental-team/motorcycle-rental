package motorent;

import motorent.builder.RentalRequest;
import motorent.builder.RentalRequestBuilder;
import motorent.factory.Motorcycle;
import motorent.factory.MotorcycleFactory;
import motorent.model.Customer;
import motorent.model.Extra;
import motorent.model.MotorcycleType;
import motorent.model.PricingPlan;

public class FactoryBuilderDemo {

    public static void main(String[] args) {
        System.out.println("=== Factory ===");
        MotorcycleFactory factory = new MotorcycleFactory();
        for (MotorcycleType type : MotorcycleType.values()) {
            Motorcycle motorcycle = factory.create(type, type.name().substring(0, 2) + "-001");
            System.out.println(motorcycle + " | hourly " + motorcycle.hourlyRate()
                    + ", daily " + motorcycle.dailyRate());
        }

        System.out.println("=== Builder ===");
        Customer alice = new Customer("Alice", "alice@mail.kz");
        RentalRequest request = new RentalRequestBuilder()
                .customer(alice)
                .motorcycleType(MotorcycleType.SPORT)
                .hours(48)
                .plan(PricingPlan.DAILY)
                .withExtra(Extra.INSURANCE)
                .withExtra(Extra.HELMET)
                .build();
        System.out.println(request);

        expectInvalid("HOURLY for 24 hours", () -> new RentalRequestBuilder()
                .customer(alice).motorcycleType(MotorcycleType.SCOOTER)
                .hours(24).plan(PricingPlan.HOURLY).build());
        expectInvalid("WEEKLY for 48 hours", () -> new RentalRequestBuilder()
                .customer(alice).motorcycleType(MotorcycleType.CRUISER)
                .hours(48).plan(PricingPlan.WEEKLY).build());
        expectInvalid("no customer", () -> new RentalRequestBuilder()
                .motorcycleType(MotorcycleType.SPORT).hours(5).build());
    }

    private static void expectInvalid(String label, Runnable invalidBuild) {
        try {
            invalidBuild.run();
            System.out.println("NOT rejected: " + label);
        } catch (IllegalStateException e) {
            System.out.println("Rejected (" + label + "): " + e.getMessage());
        }
    }
}