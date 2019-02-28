package com.fitechsource.test;

import java.util.*;

/**
 * Do not change it.
 */
public class TestCalc {
    private static final Random rnd = new Random();

    public static Set<Double> calculate(int num) throws TestException {
        // Emulates calculation delay time.
        if (rnd.nextInt(100) > 95) {
            throw new TestException("qqq");
        }
        try {
            Thread.sleep(rnd.nextInt(1000) + 1);
        }
        catch (InterruptedException e) {
            throw new TestException("Execution error.", e);
        }

        Set<Double> res = new HashSet<>();

        int n = rnd.nextInt(num + 1) + 1;

        for (int j = 0; j < n; j++) {
            res.add(rnd.nextDouble());
        }

        return res;
    }
}
