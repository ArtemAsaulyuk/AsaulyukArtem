package labs.lab4;

import java.util.ArrayList;
import java.util.List;

public class Road {
    private List<Vehicle<?>> vehicles = new ArrayList<>();

    public int getTotalPassengerCount() {
        int ret = 0;
        for (Vehicle<?> vehicle : vehicles) {
            ret += vehicle.getPassengerCount();
        }
        return ret;
    }

    public void addVehicle(Vehicle<?> vehicle) {
        vehicles.add(vehicle);
    }
}
