# Fitechsource-test-task

 Should be improved to reduce calculation time.

 1. Change this file or create new one using parallel calculation mode.
 2. Do not use `executors`, only plain threads  (max threads count which should be created for calculations is `com.fitechsource.TestConsts#MAX_THREADS)`
 3. Try to provide simple solution, do not implement frameworks.
 4. Don't forget that calculation method can throw exception, process it right way.
 (Stop calculation process and print error message. Ignore already calculated intermediate results, user doesn't need it.)

`com.fitechsource.test.Test` sourse code:

```
 package com.fitechsource.test;
 
 import java.util.*;
 
 public class Test {
     public static void main(String[] args) throws TestException {
         Set<Double> res = new HashSet<>();
 
         for (int i = 0; i < TestConsts.N; i++) {
             res.addAll(TestCalc.calculate(i));
         }
 
         System.out.println(res);
     }
 }
 ```