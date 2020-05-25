package labs.lab4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VehicleTest {

    @Test
    void testAddRemovePassengersAndRoad() {
        Firefighter tom = new Firefighter("Tom");
        Policeman sam = new Policeman("Sam");
        Human carl = new Human("Carl");
        Human chuck = new Human("Chuck");

        Bus bus = new Bus(30);
        bus.addPassenger(tom);
        bus.addPassenger(sam);
        bus.addPassenger(carl);
        bus.addPassenger(chuck);

        PoliceCar policeCar = new PoliceCar(2);
        // error
        // policeCar.addPassenger(tom);
        // error
        // policeCar.addPassenger(carl);
        // no error
        policeCar.addPassenger(sam);

        FireTruck fireTruck = new FireTruck(10);
        // error
        // fireTruck.addPassenger(sam);
        // error
        // fireTruck.addPassenger(carl);
        // no error
        fireTruck.addPassenger(tom);

        Taxi taxi = new Taxi(3);
        taxi.addPassenger(tom);
        taxi.addPassenger(sam);
        taxi.addPassenger(carl);
        assertThrows(IllegalArgumentException.class, () -> taxi.removePassenger(chuck));
        assertThrows(IllegalStateException.class, () -> taxi.addPassenger(chuck));

        Road road = new Road();
        road.addVehicle(bus);
        road.addVehicle(policeCar);
        road.addVehicle(fireTruck);
        road.addVehicle(taxi);
        assertEquals(9, road.getTotalPassengerCount());
    }
}
