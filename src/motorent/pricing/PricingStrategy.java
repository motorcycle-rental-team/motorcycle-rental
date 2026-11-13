package motorent.pricing;

import motorent.factory.Motorcycle;

public interface PricingStrategy {

    double calculate(Motorcycle motorcycle, int hours);
}
