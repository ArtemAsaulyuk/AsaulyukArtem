package labs.lab7;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MyPiCalculator {

    public static double calculatePi(int threadCount, int iterationCount) throws Exception {
        final int perThread = iterationCount / threadCount;

        AtomicInteger inRadius = new AtomicInteger(0);

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int tmp = 0;
                for (int j = 0; j < perThread; j++) {
                    double x = ThreadLocalRandom.current().nextDouble();
                    double y = ThreadLocalRandom.current().nextDouble();
                    if (Math.sqrt(x * x + y * y) <= 1.0) {
                        tmp++;
                    }
                }
                inRadius.getAndAdd(tmp);
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return inRadius.doubleValue() / iterationCount * 4;
    }
}
