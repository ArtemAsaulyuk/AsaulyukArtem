package labs.lab4;

import java.util.HashSet;
import java.util.Set;

public class Vehicle<T extends Human> {
    private Set<T> passengers;
    private int seatCount;

    public Vehicle(int seatCount) {
        this.seatCount = seatCount;
        this.passengers = new HashSet<>();
    }

    public int getPassengerCount() {
        return passengers.size();
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void addPassenger(T passenger) {
        if (passengers.size() == seatCount) {
            throw new IllegalStateException("all seats are occupied");
        }
        passengers.add(passenger);
    }

    public void removePassenger(T passenger) {
        if (!passengers.remove(passenger)) {
            throw new IllegalArgumentException("passenger is not in vehicle");
        }
    }
}
