package com.company;

public class Main {

    public static void main(String[] args) {

        //Generate tables for big integer class
        MyBigIntegerGenerateTables runTests = new MyBigIntegerGenerateTables();
        runTests.generateTable();

        //Generate tables for Fibonacci function
        BigFibonacciTables generateFibTables = new BigFibonacciTables();
        generateFibTables.generateTables();
    }
}
