package motorent.decorator;

import java.util.Objects;

public abstract class RentalDecorator implements Rental {

    protected final Rental rental;

    protected RentalDecorator(Rental rental) {
        this.rental = Objects.requireNonNull(rental, "Rental is required");
    }

    @Override
    public String description() {
        return rental.description();
    }

    @Override
    public double cost() {
        return rental.cost();
    }
}
