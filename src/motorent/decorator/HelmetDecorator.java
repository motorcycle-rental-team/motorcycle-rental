package motorent.decorator;

public class HelmetDecorator extends RentalDecorator {

    public HelmetDecorator(Rental rental) {
        super(rental);
    }

    @Override
    public String description() {
        return super.description() + ", helmet";
    }

    @Override
    public double cost() {
        return super.cost() + 5;
    }
}
