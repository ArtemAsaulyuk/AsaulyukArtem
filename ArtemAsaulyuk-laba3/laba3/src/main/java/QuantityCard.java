public class QuantityCard extends Card {
    private int numberOfTrips;

    public QuantityCard(int id) {
        super(id, Type.BASE);
    }

    public QuantityCard(int id, Type type) {
        super(id, type);
    }

    @Override
    public boolean pass() {
        if (numberOfTrips > 0 && isAvailable()) {
            numberOfTrips--;
            return true;
        }

        return false;
    }

    public void addTrips(int amount) {
        if (amount >= 0)
            numberOfTrips += amount;
    }

    public int getNumberOfTrips() {
        return numberOfTrips;
    }

    @Override
    public String toString() {
        return "QuantityCard: " +
                super.toString() +
                ", number of trips=" + numberOfTrips;
    }
}
