package motorent.pricing;

import motorent.factory.Motorcycle;

import java.util.Objects;

public class HourlyPricingStrategy implements PricingStrategy {

    @Override
    public double calculate(Motorcycle motorcycle, int hours) {
        Objects.requireNonNull(motorcycle, "Motorcycle is required");
        if (hours <= 0 || hours > 12) {
            throw new IllegalArgumentException("HOURLY plan requires 1 to 12 hours");
        }
        return motorcycle.hourlyRate() * hours;
    }
}
