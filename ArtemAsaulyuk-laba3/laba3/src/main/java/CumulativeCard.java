public class CumulativeCard extends Card {

    private double amount;

    private static double paymentForPass = 10;

    public CumulativeCard(int id) {
        super(id, Type.BASE);
    }

    public CumulativeCard(int id, Type type) {
        super(id, type);
    }

    @Override
    public boolean pass() {
        double passFee = CumulativeCard.paymentForPass;
        /*if (getType() == Type.PRIVILEGE) {
            passFee = passFee / 2;
        }*/

        if (amount >= passFee && isAvailable()) {
            amount -= passFee;
            return true;
        }

        return false;
    }

    public void addAmount(double amount) {
        this.amount += amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "CumulativeCard: " +
                super.toString() +
                ", amount=" + amount;
    }
}
