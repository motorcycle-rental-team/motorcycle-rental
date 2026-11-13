package motorent.decorator;

import motorent.model.Extra;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class RentalExtras {

    public Rental apply(Rental rental, Set<Extra> extras) {
        Objects.requireNonNull(rental, "Rental is required");
        Objects.requireNonNull(extras, "Extras are required");
        Set<Extra> selected = EnumSet.noneOf(Extra.class);
        selected.addAll(extras);
        for (Extra extra : selected) {
            rental = switch (extra) {
                case HELMET -> new HelmetDecorator(rental);
                case INSURANCE -> new InsuranceDecorator(rental);
                case GPS -> new GpsDecorator(rental);
            };
        }
        return rental;
    }
}
