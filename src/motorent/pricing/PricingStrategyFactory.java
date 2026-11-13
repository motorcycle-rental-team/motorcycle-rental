package motorent.pricing;

import motorent.model.PricingPlan;

import java.util.Objects;

public class PricingStrategyFactory {

    public PricingStrategy create(PricingPlan plan) {
        Objects.requireNonNull(plan, "Pricing plan is required");
        return switch (plan) {
            case HOURLY -> new HourlyPricingStrategy();
            case DAILY -> new DailyPricingStrategy();
            case WEEKLY -> new WeeklyPricingStrategy();
        };
    }
}
