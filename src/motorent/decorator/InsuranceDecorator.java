package motorent.decorator;

public class InsuranceDecorator extends RentalDecorator {

    public InsuranceDecorator(Rental rental) {
        super(rental);
    }

    @Override
    public String description() {
        return super.description() + ", insurance";
    }

    @Override
    public double cost() {
        return super.cost() + 15;
    }
}
