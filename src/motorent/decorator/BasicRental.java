package motorent.decorator;

import motorent.factory.Motorcycle;
import motorent.pricing.PricingStrategy;

import java.util.Objects;

public class BasicRental implements Rental {

    private final Motorcycle motorcycle;
    private final int hours;
    private final PricingStrategy pricingStrategy;

    public BasicRental(Motorcycle motorcycle, int hours, PricingStrategy pricingStrategy) {
        this.motorcycle = Objects.requireNonNull(motorcycle, "Motorcycle is required");
        this.pricingStrategy = Objects.requireNonNull(pricingStrategy, "Pricing strategy is required");
        if (hours <= 0) {
            throw new IllegalArgumentException("Hours must be positive");
        }
        this.hours = hours;
    }

    @Override
    public String description() {
        return motorcycle + " for " + hours + " hour(s)";
    }

    @Override
    public double cost() {
        return pricingStrategy.calculate(motorcycle, hours);
    }
}
