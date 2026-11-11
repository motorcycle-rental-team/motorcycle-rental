package motorent.factory;

import motorent.model.MotorcycleType;

public abstract class Motorcycle {

    private final String plate;

    protected Motorcycle(String plate) {
        if (plate == null || plate.isBlank()) {
            throw new IllegalArgumentException("Plate must not be blank");
        }
        this.plate = plate;
    }

    public String plate() {
        return plate;
    }

    public abstract MotorcycleType type();

    public abstract String model();

    public abstract double hourlyRate();

    public abstract double dailyRate();

    @Override
    public String toString() {
        return type() + " " + model() + " (" + plate + ")";
    }
}