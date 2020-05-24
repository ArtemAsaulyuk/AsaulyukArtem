import org.junit.Assert;
import org.junit.Test;

public class TestCumulativeCard {
    @Test
    public void testPass() {
        CumulativeCard expectedBase = new CumulativeCard(1, Card.Type.BASE);
        expectedBase.setAmount(60);
        testCard(Card.Type.BASE, expectedBase);

        CumulativeCard expectedPrivilege = new CumulativeCard(1, Card.Type.PRIVILEGE);
        expectedPrivilege.addAmount(80);
        testCard(Card.Type.PRIVILEGE, expectedPrivilege);
    }

    private void testCard(Card.Type type, CumulativeCard expected) {
        CumulativeCard cumulativeCard = new CumulativeCard(1, type);
        cumulativeCard.setAmount(100);
        for (int i = 0; i < 4; i++) {
            cumulativeCard.pass();
        }

        boolean isPassed = cumulativeCard.pass();
        if (!isPassed) {
            Assert.fail(cumulativeCard.toString() + " is not passed");
        }

        cumulativeCard.setAvailable(false);
        isPassed = cumulativeCard.pass();
        if (isPassed) {
            Assert.fail(cumulativeCard.toString() + " locked card");
        }
        cumulativeCard.setAvailable(true);

        Assert.assertEquals(expected, cumulativeCard);
    }
}
