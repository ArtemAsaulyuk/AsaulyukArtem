package labs.lab7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyPiCalculatorTest {

    @Test
    void testCalculatePi() throws Exception {
        double pi = MyPiCalculator.calculatePi(8, 1_000_000_000);
        assertEquals("3.14", Double.toString(pi).substring(0, 4));
    }
}
