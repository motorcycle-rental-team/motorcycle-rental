package motorent.factory;

import motorent.model.MotorcycleType;

public class SportBike extends Motorcycle {

    public SportBike(String plate) {
        super(plate);
    }

    @Override
    public MotorcycleType type() {
        return MotorcycleType.SPORT;
    }

    @Override
    public String model() {
        return "Kawasaki H2";
    }

    @Override
    public double hourlyRate() {
        return 10;
    }

    @Override
    public double dailyRate() {
        return 80;
    }
}
