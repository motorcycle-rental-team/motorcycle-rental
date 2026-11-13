package motorent.pricing;

import motorent.factory.Motorcycle;

import java.util.Objects;

public class DailyPricingStrategy implements PricingStrategy {

    @Override
    public double calculate(Motorcycle motorcycle, int hours) {
        Objects.requireNonNull(motorcycle, "Motorcycle is required");
        if (hours <= 0) {
            throw new IllegalArgumentException("Hours must be positive");
        }
        return motorcycle.dailyRate() * Math.ceil(hours / 24.0);
    }
}
