package motorent.builder;

import motorent.model.Customer;
import motorent.model.Extra;
import motorent.model.MotorcycleType;
import motorent.model.PricingPlan;

import java.util.EnumSet;
import java.util.Set;

public class RentalRequestBuilder {

    private static final int MAX_HOURLY_HOURS = 12;
    private static final int MIN_WEEKLY_HOURS = 168;

    private Customer customer;
    private MotorcycleType type;
    private int hours;
    private PricingPlan plan = PricingPlan.DAILY;
    private final Set<Extra> extras = EnumSet.noneOf(Extra.class);

    public RentalRequestBuilder customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public RentalRequestBuilder motorcycleType(MotorcycleType type) {
        this.type = type;
        return this;
    }

    public RentalRequestBuilder hours(int hours) {
        this.hours = hours;
        return this;
    }

    public RentalRequestBuilder plan(PricingPlan plan) {
        this.plan = plan;
        return this;
    }

    public RentalRequestBuilder withExtra(Extra extra) {
        if (extra == null) {
            throw new IllegalArgumentException("Extra must not be null");
        }
        extras.add(extra);
        return this;
    }

    public RentalRequest build() {
        validateState();
        return new RentalRequest(customer, type, hours, plan, extras);
    }

    private void validateState() {
        if (customer == null) {
            throw new IllegalStateException("Customer is required");
        }
        if (type == null) {
            throw new IllegalStateException("Motorcycle type is required");
        }
        if (plan == null) {
            throw new IllegalStateException("Pricing plan is required");
        }
        if (hours <= 0) {
            throw new IllegalStateException("Hours must be positive");
        }
        if (plan == PricingPlan.HOURLY && hours > MAX_HOURLY_HOURS) {
            throw new IllegalStateException("HOURLY plan is only for rentals up to " + MAX_HOURLY_HOURS + " hours");
        }
        if (plan == PricingPlan.WEEKLY && hours < MIN_WEEKLY_HOURS) {
            throw new IllegalStateException("WEEKLY plan needs at least " + MIN_WEEKLY_HOURS + " hours");
        }
    }
}