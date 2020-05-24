import org.junit.Assert;
import org.junit.Test;

public class TestQuantityCard {
    @Test
    public void testPassWithTrips() {
        QuantityCard quantityCard = new QuantityCard(1);
        quantityCard.addTrips(10);
        for (int i = 0; i < 10; i++) {
            boolean isPassed = quantityCard.pass();
            if (!isPassed) {
                Assert.fail(quantityCard.toString() + " not passed");
            }
        }
    }

    @Test
    public void testPassWithoutTrips() {
        QuantityCard quantityCard = new QuantityCard(1);
        boolean isPassed = quantityCard.pass();
        if (isPassed) {
            Assert.fail(quantityCard.toString() + " passed");
        }
    }

    @Test
    public void testPassOnUnavailableCard() {
        QuantityCard quantityCard = new QuantityCard(1);
        quantityCard.addTrips(1);
        quantityCard.setAvailable(false);

        boolean isPassed = quantityCard.pass();
        if (isPassed) {
            Assert.fail(quantityCard.toString() + " locked card");
        }

        quantityCard.setAvailable(true);
        isPassed = quantityCard.pass();
        if (!isPassed) {
            Assert.fail(quantityCard.toString() + " not passed");
        }
    }
}
