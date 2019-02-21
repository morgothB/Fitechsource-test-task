package com.fitechsource.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Should be improved to reduce calculation time.
 * <p>
 * 1. Change this file or create new one using parallel calculation mode.
 * 2. Do not use `executors`, only plain threads  (max threads count which should be created for calculations is com.fitechsource.TestConsts#MAX_THREADS)
 * 3. Try to provide simple solution, do not implement frameworks.
 * 4. Don't forget that calculation method can throw exception, process it right way.
 * (Stop calculation process and print error message. Ignore already calculated intermediate results, user doesn't need it.)
 * <p>
 */

public class Test {

    public static void main(String[] args) throws TestException {
        long tmp = System.currentTimeMillis();
        Set<Double> res = new ConcurrentSkipListSet<>();
        AtomicInteger calcRemaining = new AtomicInteger(TestConsts.N);
        //AtomicInteger calcCounter = new AtomicInteger(0);
        ArrayList<Thread> threads = new ArrayList<>();
        Runnable r = () -> {
            try {
                int val = calcRemaining.getAndDecrement();
                while (val > 0) {
                    res.addAll(TestCalc.calculate(val));
                    val = calcRemaining.getAndDecrement();
                    // calcCounter.incrementAndGet();
                }

            } catch (TestException e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i < TestConsts.MAX_THREADS; i++) {
            threads.add(new Thread(r));
            threads.get(i).start();
        }
        for (Thread thread : threads) {
            try {
                if (!thread.isInterrupted()) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(res);
        // System.out.println(calcCounter);
        print(System.currentTimeMillis() - tmp);
        run(new HashSet<>());
    }

    public static void run(Collection<Double> res) throws TestException {
        long tmp = System.currentTimeMillis();
        for (int i = 0; i < TestConsts.N; i++) {
            res.addAll(TestCalc.calculate(i));
        }
        System.out.println(res);
        print(System.currentTimeMillis() - tmp);
    }

    public static String print(long tmp) {
        Date time = new Date(tmp);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateFormatted = formatter.format(time);
        System.out.println(dateFormatted);
        System.out.println();
        return dateFormatted;
    }

}
