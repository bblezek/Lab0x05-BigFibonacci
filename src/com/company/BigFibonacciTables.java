package com.company;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class BigFibonacciTables {

    //Function to retrieve CPU time
    public static long getCpuTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadCpuTime() : 0L;
    }

    public void generateTables(){
        long[] prevTime = new long[10];
        long curRunTime = 0;
        long beforeTime, afterTime;
        long maxRunTime = 300000000000L;
        long max = 100;//Long.MAX_VALUE;
        MyBigInteger fibIdx = new MyBigInteger();
        MyBigInteger result;
        BigFibonacci bigFib = new BigFibonacci();
        long N;
        int X;
        String[] xValues = new String[10];

        //Print first row of table
        System.out.printf("%15s %15s %15s %15s %15s %15s %15s\n", "N (size)", "X (input value)", "fib(X)",
                "Run Time", "10x Ratio:", "T_x-based", "T_n-based");
        System.out.printf("%79s %15s\n", "Expected", "Expected");
        System.out.printf("%79s %15s\n", "10x Ratio", "+1 Ratio");

        //Populating xValues with initial values;
        for(X = 0; X < 10; X++){
            xValues[X] = String.valueOf(X);
        }

        //N is the number of digits
        for (N = 1; N < max; N = N * 2) {
            if (curRunTime >= maxRunTime) {
                break;
            }
            //X iterates through each value in the digit (1..9 + appropriate number of zeros)
            for(X = 1; X<=9; X++) {
                fibIdx.setValue(xValues[X]);
                beforeTime = getCpuTime();
                result = bigFib.fibLoopBig(fibIdx);
                afterTime = getCpuTime();
                curRunTime = afterTime - beforeTime;
                System.out.printf("%15d %15s %15s %15d ", N, fibIdx.AbbreviatedValue(), result.AbbreviatedValue(),
                        curRunTime);
                if (N > 1) {
                    //Because the complexity is linear, it should take 10 times as long as it took for X/10
                    System.out.printf("%15.3f %15d %15.3f \n", (float) curRunTime / prevTime[X], 10, Math.pow(10, 2*N)/Math.pow(10, 2*(N-1)));
                } else {
                    System.out.printf("%15s %15s %15s\n", "na", "na", "na");
                }
                prevTime[X] = curRunTime;
                xValues[X] = xValues[X] + "0";
            }
        }
    }
}
