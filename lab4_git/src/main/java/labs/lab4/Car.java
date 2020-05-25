package labs.lab4;

public class Car<T extends Human> extends Vehicle<T> {
    public Car(int seatCount) {
        super(seatCount);
    }
}
