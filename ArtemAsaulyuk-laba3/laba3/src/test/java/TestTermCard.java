import org.junit.Assert;
import org.junit.Test;
import java.util.Date;

public class TestTermCard {
    @Test
    public void testPass() {
        TermCard termCard = new TermCard(1);
        Date validity = new Date(System.currentTimeMillis() + 604_800_000);
        termCard.setValidity(validity);

        boolean isPassed = termCard.pass();
        if (!isPassed) {
            Assert.fail(termCard.toString() + " not passed");
        }

        termCard.setValidity(new Date(System.currentTimeMillis() - 1000));
        isPassed = termCard.pass();
        if (isPassed) {
            Assert.fail(termCard.toString() + " passed after validity date");
        }
        termCard.setValidity(validity);
    }

    @Test
    public void testPassOnUnavailableCard() {
        TermCard termCard = new TermCard(1);
        Date validity = new Date(System.currentTimeMillis() + 604_800_000);
        termCard.setValidity(validity);

        termCard.setAvailable(false);
        boolean isPassed = termCard.pass();
        if (isPassed) {
            Assert.fail(termCard.toString() + " locked card");
        }
        termCard.setAvailable(true);

        isPassed = termCard.pass();
        if (!isPassed) {
            Assert.fail(termCard.toString() + " not passed");
        }
    }
}
