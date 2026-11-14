package motorent.facade;

import motorent.factory.Motorcycle;
import motorent.model.Customer;
import motorent.model.MotorcycleType;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

final class RentalInventory {

    record ActiveRental(Motorcycle motorcycle, Customer customer) {
    }

    private final Map<String, Motorcycle> motorcycles = new LinkedHashMap<>();
    private final Map<String, ActiveRental> activeRentals = new HashMap<>();

    RentalInventory(List<Motorcycle> fleet) {
        for (Motorcycle motorcycle : List.copyOf(fleet)) {
            if (motorcycles.putIfAbsent(motorcycle.plate(), motorcycle) != null) {
                throw new IllegalArgumentException("Duplicate plate: " + motorcycle.plate());
            }
        }
    }

    Motorcycle findAvailable(MotorcycleType type) {
        Objects.requireNonNull(type, "Motorcycle type is required");
        for (Motorcycle motorcycle : motorcycles.values()) {
            if (motorcycle.type() == type && !activeRentals.containsKey(motorcycle.plate())) {
                return motorcycle;
            }
        }
        throw new IllegalStateException("No available motorcycle of type " + type);
    }

    void registerRental(Motorcycle motorcycle, Customer customer) {
        activeRentals.put(motorcycle.plate(), new ActiveRental(motorcycle, customer));
    }

    ActiveRental release(String plate) {
        ActiveRental rental = activeRentals.remove(plate);
        if (rental == null) {
            throw new IllegalStateException("No active rental for plate " + plate);
        }
        return rental;
    }
}
