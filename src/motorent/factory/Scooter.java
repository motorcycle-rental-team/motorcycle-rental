package motorent.factory;

import motorent.model.MotorcycleType;

public class Scooter extends Motorcycle {

    public Scooter(String plate) {
        super(plate);
    }

    @Override
    public MotorcycleType type() {
        return MotorcycleType.SCOOTER;
    }

    @Override
    public String model() {
        return "Samurai X7 ";
    }

    @Override
    public double hourlyRate() {
        return 4;
    }

    @Override
    public double dailyRate() {
        return 28;
    }
}