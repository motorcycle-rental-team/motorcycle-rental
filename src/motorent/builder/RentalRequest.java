package motorent.builder;

import motorent.model.Customer;
import motorent.model.Extra;
import motorent.model.MotorcycleType;
import motorent.model.PricingPlan;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public record RentalRequest(Customer customer, MotorcycleType type, int hours,
                            PricingPlan plan, Set<Extra> extras) {

    public RentalRequest {
        Set<Extra> copy = EnumSet.noneOf(Extra.class);
        copy.addAll(extras);
        extras = Collections.unmodifiableSet(copy);
    }
}