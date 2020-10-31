package com.company;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.math.BigInteger;
import java.util.Random;

public class MyBigIntegerRuntime {

    //Shamelessly copied from https://www.baeldung.com/java-random-string
    public String generateDigitString(long digits) {
        int leftLimit = 48; //0
        int rightLimit = 57; //9
        String generatedString = new Random().ints(leftLimit, rightLimit + 1)
                .limit(digits)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

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

    public void generateTable() {
        long prevTime = 0;
        long curRunTime = 0;
        long beforeTime, afterTime;
        long maxRunTime = 300000000000L;
        long max = 100;//Long.MAX_VALUE;
        MyBigInteger myBigIntOne = new MyBigInteger();
        MyBigInteger myBigIntTwo = new MyBigInteger();
        MyBigInteger myBigIntResult;
        long N;

        //Print first row of table
        System.out.printf("%15s %15s %15s %15s %15s\n", "N", "X_1", "Time", "Doubling", "Expected");
        System.out.printf("%31s %31s %15s\n", "X_2", "Ratio", "Doubling");
        System.out.printf("%77s\n", "Ratio");


        for (N = 1; N < max; N = N * 2) {
            if (curRunTime >= maxRunTime) {
                break;
            }
                myBigIntOne.setValue(generateDigitString(N));
                myBigIntTwo.setValue(generateDigitString(N));
                beforeTime = getCpuTime();
                myBigIntResult = myBigIntOne.MyBigIntegerPlus(myBigIntTwo);
                afterTime = getCpuTime();
                curRunTime = afterTime - beforeTime;
                System.out.printf("%15d %15s %15d ", N, myBigIntOne.AbbreviatedValue(), curRunTime);
                if (N > 1) {
                    System.out.printf("%15.3f %15d\n", (float) curRunTime / prevTime, 2);
                } else {
                    System.out.printf("%15s %15s\n", "na", "na");
                }
                System.out.printf("%31s\n", myBigIntTwo.AbbreviatedValue());
                prevTime = curRunTime;
        }

        //Generate table for slower multiplication table
        prevTime = 0;
        for (N = 1; N < max; N = N * 2) {
            if (prevTime >= maxRunTime) {
                break;
            }
                myBigIntOne.setValue(generateDigitString(N));
                myBigIntTwo.setValue(generateDigitString(N));
                beforeTime = getCpuTime();
                myBigIntResult = myBigIntOne.MyBigIntegerTimes(myBigIntTwo);
                afterTime = getCpuTime();
                curRunTime = afterTime - beforeTime;
                System.out.printf("%15d %15s %15d ", N, myBigIntOne.AbbreviatedValue(), curRunTime);
                if (N > 1) {
                    //Time complexity is N^2 / (N/2)^2
                    System.out.printf("%15.3f %15d\n", (float) curRunTime / prevTime, (N * N) / (N * N / 4));
                } else {
                    System.out.printf("%15s %15s\n", "na", "na");
                }
                System.out.printf("%31s\n", myBigIntTwo.AbbreviatedValue());
                prevTime = curRunTime;
        }

        prevTime = 0;
        //Generate table for fastest multiplication table
        for (N = 1; N < max; N = N * 2) {
            if (prevTime >= maxRunTime) {
                break;
            }
            myBigIntOne.setValue(generateDigitString(N));
            myBigIntTwo.setValue(generateDigitString(N));
            beforeTime = getCpuTime();
            myBigIntResult = myBigIntOne.MyBigIntegerPlus(myBigIntTwo);
            afterTime = getCpuTime();
            curRunTime = afterTime - beforeTime;
            System.out.printf("%15d %15s %15d ", N, myBigIntOne.AbbreviatedValue(), curRunTime);
            if (N > 1) {
                System.out.printf("%15.3f %15.0f\n", (float) curRunTime / prevTime,
                        Math.pow(N, logBaseTwo(3))/Math.pow(N/2, logBaseTwo(3)));
            } else {
                System.out.printf("%15s %15s\n", "na", "na");
            }
            System.out.printf("%31s\n", myBigIntTwo.AbbreviatedValue());
            prevTime = curRunTime;
        }
    }

}
