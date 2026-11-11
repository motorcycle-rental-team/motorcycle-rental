package motorent.factory;

import motorent.model.MotorcycleType;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class MotorcycleFactory {

    private final Map<MotorcycleType, Function<String, Motorcycle>> creators = new EnumMap<>(MotorcycleType.class);

    public MotorcycleFactory() {
        creators.put(MotorcycleType.SPORT, SportBike::new);
        creators.put(MotorcycleType.CRUISER, Cruiser::new);
        creators.put(MotorcycleType.SCOOTER, Scooter::new);
    }

    public Motorcycle create(MotorcycleType type, String plate) {
        Function<String, Motorcycle> creator = creators.get(type);
        if (creator == null) {
            throw new IllegalArgumentException("Unknown motorcycle type: " + type);
        }
        return creator.apply(plate);
    }
}