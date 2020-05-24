import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCardRegister {
    @Test
    public void testCreateCard() {
        List<Card> expected = new ArrayList<>();
        expected.add(new TermCard(1));
        expected.add(new QuantityCard(2));
        expected.add(new CumulativeCard(3));
        expected.add(new TermCard(4, Card.Type.PRIVILEGE));

        CardsRegister register = new CardsRegister();
        register.createCard(1, Card.Model.TERM);
        register.createCard(2, Card.Model.QUANTITY);
        register.createCard(3, Card.Model.CUMULATIVE);
        register.createCard(4, Card.Model.TERM, Card.Type.PRIVILEGE);

        Assert.assertEquals(expected, register.getCards());
    }

    @Test
    public void testLog() {
        CardsRegister register = new CardsRegister();
        createLogs(register);
        Date termCardValidity = new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(10));
        TermCard termCard = new TermCard(4, termCardValidity);
        register.log(termCard, true);

        List<String> expected = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        expected.add("CumulativeCard: id=1, type=BASE, available=true, amount=1000.0, passed=true");
        expected.add("CumulativeCard: id=2, type=PRIVILEGE, available=true, amount=700.0, passed=true");
        expected.add("QuantityCard: id=3, type=BASE, available=true, number of trips=0, passed=false");
        expected.add("CumulativeCard: id=1, type=BASE, available=true, amount=1000.0, passed=false");
        expected.add("TermCard: id=4, type=BASE, available=true, validity=" + dateFormat.format(termCardValidity) + ", passed=true");

        List<String> actual = register.getLogs();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetLogsForType() {
        CardsRegister register = new CardsRegister();
        createLogs(register);

        List<String> expectedForBase = new ArrayList<>();
        expectedForBase.add("CumulativeCard: id=1, type=BASE, available=true, amount=1000.0, passed=true");
        expectedForBase.add("QuantityCard: id=3, type=BASE, available=true, number of trips=0, passed=false");
        expectedForBase.add("CumulativeCard: id=1, type=BASE, available=true, amount=1000.0, passed=false");
        Assert.assertEquals(expectedForBase, register.getLogs(Card.Type.BASE));

        List<String> expectedForPrivilege = new ArrayList<>();
        expectedForPrivilege.add("CumulativeCard: id=2, type=PRIVILEGE, available=true, amount=700.0, passed=true");
        Assert.assertEquals(expectedForPrivilege, register.getLogs(Card.Type.PRIVILEGE));
    }

    @Test
    public void testGetLogsForCard() {
        CardsRegister register = new CardsRegister();
        createLogs(register);

        CumulativeCard cumulativeCard1 = new CumulativeCard(1);
        cumulativeCard1.setAmount(1000);
        CumulativeCard cumulativeCard2 = new CumulativeCard(2, Card.Type.PRIVILEGE);
        cumulativeCard2.setAmount(700);
        QuantityCard quantityCard = new QuantityCard(3);

        List<String> expected1 = new ArrayList<>();
        expected1.add("CumulativeCard: id=1, type=BASE, available=true, amount=1000.0, passed=true");
        expected1.add("CumulativeCard: id=1, type=BASE, available=true, amount=1000.0, passed=false");
        Assert.assertEquals(expected1, register.getLogs(cumulativeCard1));

        List<String> expected2 = new ArrayList<>();
        expected2.add("CumulativeCard: id=2, type=PRIVILEGE, available=true, amount=700.0, passed=true");
        Assert.assertEquals(expected2, register.getLogs(cumulativeCard2));

        List<String> expected3 = new ArrayList<>();
        expected3.add("QuantityCard: id=3, type=BASE, available=true, number of trips=0, passed=false");
        Assert.assertEquals(expected3, register.getLogs(quantityCard));
    }

    private void createLogs(CardsRegister register) {
        CumulativeCard cumulativeCard1 = new CumulativeCard(1);
        cumulativeCard1.setAmount(1000);
        CumulativeCard cumulativeCard2 = new CumulativeCard(2, Card.Type.PRIVILEGE);
        cumulativeCard2.setAmount(700);
        QuantityCard quantityCard = new QuantityCard(3);

        register.log(cumulativeCard1, true);
        register.log(cumulativeCard2, true);
        register.log(quantityCard, false);
        register.log(cumulativeCard1, false);
    }


}
