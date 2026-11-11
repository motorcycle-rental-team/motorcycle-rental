package motorent.factory;

import motorent.model.MotorcycleType;

public class Cruiser extends Motorcycle {

    public Cruiser(String plate) {
        super(plate);
    }

    @Override
    public MotorcycleType type() {
        return MotorcycleType.CRUISER;
    }

    @Override
    public String model() {
        return "Harley-Davidson Sportster";
    }

    @Override
    public double hourlyRate() {
        return 8;
    }

    @Override
    public double dailyRate() {
        return 65;
    }
}