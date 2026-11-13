package motorent.decorator;

public class GpsDecorator extends RentalDecorator {

    public GpsDecorator(Rental rental) {
        super(rental);
    }

    @Override
    public String description() {
        return super.description() + ", GPS";
    }

    @Override
    public double cost() {
        return super.cost() + 7;
    }
}
