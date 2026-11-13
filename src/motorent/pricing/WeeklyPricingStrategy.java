package motorent.pricing;

import motorent.factory.Motorcycle;

import java.util.Objects;

public class WeeklyPricingStrategy implements PricingStrategy {

    @Override
    public double calculate(Motorcycle motorcycle, int hours) {
        Objects.requireNonNull(motorcycle, "Motorcycle is required");
        if (hours < 168) {
            throw new IllegalArgumentException("WEEKLY plan requires at least 168 hours");
        }
        return motorcycle.dailyRate() * 7 * Math.ceil(hours / 168.0);
    }
}
