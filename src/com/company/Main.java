package com.company;

public class Main {



    public static void main(String[] args) {
	// write your code here
        long max = Integer.MAX_VALUE + 1;
        String A = "2147483648089";
        MyBigInteger a = new MyBigInteger(A);
        String B = "51474836480";
        MyBigInteger b = new MyBigInteger(B);
        System.out.printf("Original string is %s \n", a.toString());
        System.out.printf("B string is %s \n", b.toString());
        long result;
        result = 2147483648089L + 51474836480L;
        MyBigInteger resultBigInt = a.MyBigIntegerPlus(b);
        System.out.printf("Result is %s \n", resultBigInt.toString());
        System.out.printf("Result should be %d \n", result);
    }
}
