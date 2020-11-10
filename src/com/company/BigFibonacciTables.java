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

    //Calculates log base 2 of N
    public static float logBaseTwo(long N) {
        float result = ((float) Math.log(N) / (float) Math.log(2));
        return result;
    }

    public void generateTables() {
        long[] prevTime = new long[10];
        long curRunTime = 0;
        long beforeTime, afterTime;
        long maxRunTime = 300000000000L;
        long max = Long.MAX_VALUE;
        MyBigInteger fibIdx = new MyBigInteger();
        MyBigInteger result;
        BigFibonacci bigFib = new BigFibonacci();
        long N;
        int X;
        String[] xValues = new String[10];
        
        /*//Print first row of table
        System.out.printf("Fibonacci Big Integer Loop Table:\n");
        System.out.printf("%15s %15s %15s %15s %15s %15s %15s\n", "N (size)", "X (input value)", "fib(X)",
                "Run Time", "10x Ratio:", "T_x-based", "T_n-based");
        System.out.printf("%79s %15s\n", "Expected", "Expected");
        System.out.printf("%79s %15s\n", "10x Ratio", "+1 Ratio");

        //Populating xValues with initial values;
        for (X = 0; X < 10; X++) {
            xValues[X] = String.valueOf(X);
        }

        //N is the number of digits
        for (N = 1; N < max; N++) {
            if (curRunTime >= maxRunTime) {
                break;
            }
            //X iterates through each value in the digit (1..9 + appropriate number of zeros)
            for (X = 1; X <= 9; X++) {
                fibIdx.setValue(xValues[X]);
                beforeTime = getCpuTime();
                result = bigFib.fibLoopBig(fibIdx);
                afterTime = getCpuTime();
                curRunTime = afterTime - beforeTime;
                System.out.printf("%15d %15s %15s %15d ", N, fibIdx.AbbreviatedValue(), result.AbbreviatedValue(),
                        curRunTime);
                if (N > 1) {
                    //The complexity is quadratic, because for each iteration through the loop we have to
                    //perform an addition which is also linear in proportion to X.
                    // So our complexity as compared to X/10 will be
                    // X^2/ (X/10)^2 or X^2/(X^2/100) or 100
                    System.out.printf("%15.3f %15d %15.0f \n", (float) curRunTime / prevTime[X], 100,
                            Math.pow(10, 2 * N) / Math.pow(10, 2 * (N - 1)));
                } else {
                    System.out.printf("%15s %15s %15s\n", "na", "na", "na");
                }
                prevTime[X] = curRunTime;
                xValues[X] = xValues[X] + "0";
            }
        }
*/
        //Table for fibMatrixBig
        //Print first row of table
        System.out.printf("Fibonacci Big Integer Matrix Table:\n");
        System.out.printf("%15s %15s %15s %15s %15s %15s %15s\n", "N (size)", "X (input value)", "fib(X)",
                "Run Time", "10x Ratio:", "T_x-based", "T_n-based");
        System.out.printf("%94s %15s\n", "Expected", "Expected");
        System.out.printf("%94s %15s\n", "10x Ratio", "+1 Ratio");

        //Populating xValues with initial values;
        for (X = 0; X < 10; X++) {
            xValues[X] = String.valueOf(X);
        }

        long XVal, NVal;
        result = new MyBigInteger();
        //N is the number of digits
        for (N = 1; N < max; N++) {
            if (curRunTime >= maxRunTime) {
                break;
            }
            //X iterates through each value in the digit (1..9 + appropriate number of zeros)
            for (X = 1; X <= 9; X++) {

                fibIdx.setValue(xValues[X]);
                if(N <= 4){
                    curRunTime = 0;
                    for(int y = 0; y < 100; y++) {
                        beforeTime = getCpuTime();
                        result = bigFib.fibMatrixBig(fibIdx);
                        afterTime = getCpuTime();
                        curRunTime = curRunTime + afterTime - beforeTime;
                    }
                    curRunTime = curRunTime/100;
                } else {
                    beforeTime = getCpuTime();
                    result = bigFib.fibMatrixBig(fibIdx);
                    afterTime = getCpuTime();
                    curRunTime = afterTime - beforeTime;
                }
                System.out.printf("%15d %15s %15s %15d ", N, fibIdx.AbbreviatedValue(), result.AbbreviatedValue(),
                        curRunTime);

                if (N > 1) {
                    //The complexity for the matrix fibonacci function is log base 2 (binary digits)
                    //For each binary digit, we perform 1 or 2 matrix multiplications, which are comprised
                    //of 4 addition (linear) and 4 multiplications (of complexity X^log_2(3))
                    //so total complexity is X * X^log_2(3)

                    //Holds actual value of X (with appropriate number of digits
                    XVal = (long) (X * Math.pow(10, N-1));

                    //Holds actual value of N with appropriate number of digits
                    NVal = (long) Math.pow(10, N-1);
                    System.out.printf("%15.3f %15.3f %15.3f \n", (float) curRunTime / prevTime[X],
                            logBaseTwo(XVal) * Math.pow(XVal, logBaseTwo(3)) /
                                    (logBaseTwo(XVal / 10) * Math.pow(XVal / 10, logBaseTwo(3))),
                            //Based on 10^N-1 so not precise
                            logBaseTwo(NVal) * Math.pow(NVal, logBaseTwo(3)) /
                                    (logBaseTwo(NVal/10) * Math.pow(NVal/10, logBaseTwo(3))));
                } else {
                    System.out.printf("%15s %15s %15s\n", "na", "na", "na");
                }
                //Setting appropriate place in array to run time for next iteration
                prevTime[X] = curRunTime;
                //Prepping "X" value for next iteration
                xValues[X] = xValues[X] + "0";
            }
        }
    }
}
