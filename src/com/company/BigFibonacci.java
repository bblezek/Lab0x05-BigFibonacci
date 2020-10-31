package com.company;

public class BigFibonacci {

    //Matrix multiplication function
    public static MyBigInteger[][] matrixMulBig(MyBigInteger[][] matrixOne, MyBigInteger[][] matrixTwo,
                                        int oneRows, int oneCols, int twoRows, int twoCols) {
        //Creating a new matrix to store our results
        MyBigInteger[][] resultMatrix = new MyBigInteger[oneRows][twoCols];
        MyBigInteger result = new MyBigInteger();
        //Ensuring that we can multiply the matrix
        if (oneCols != twoRows) {
            System.out.printf("Incorrect matrix arrays!! \n");
            return resultMatrix;
        }

        //Select row of matrix one
        for (int oneRow = 0; oneRow < oneRows; oneRow++) {
            //Select column of matrix two
            for (int twoCol = 0; twoCol < twoCols; twoCol++) {
                result.setValue("0");
                //Iterate over row and column, adding to result
                for (int sharedDim = 0; sharedDim < oneCols; sharedDim++) {
                    result = result.MyBigIntegerPlus(matrixOne[oneRow][sharedDim].MyBigIntegerFasterTimes(matrixTwo[sharedDim][twoCol]));
                }
                //Setting spot in result matrix equal to result
                resultMatrix[oneRow][twoCol] = new MyBigInteger(result.Value());
            }
        }
        return resultMatrix;
    }

    //Fibonacci sequence function based on matrices
    public static MyBigInteger fibMatrixBig(MyBigInteger x) {
        //This matrix will store our "powers" as we calculate them
        MyBigInteger[][] powerMatrix = {{new MyBigInteger("1"), new MyBigInteger("1")},
                {new MyBigInteger("1"), new MyBigInteger("0")}};
        //This matrix will store our final result matrix
        MyBigInteger[][] resultMatrix = {{new MyBigInteger("1"), new MyBigInteger("1")},
                {new MyBigInteger("1"), new MyBigInteger("0")}};

        //We only need to iterate to matrix "power" number - 2
        //This is because the first value of the sequence is 0 and the second value is 1, so we can't technically
        //perform a matrix multiplication for those two "powers" and thus the sequence of matrix powers starts at
        //index 3 with "M to the first"
        long y = Long.parseLong(x.Value()) - 2;
        //Return 0 if number equals 0
        if (x.Value().equals("0")) {
            return x;
            //Otherwise, iterate over "binary digits" of num to find result matrix
        } else {
            //If x is index 1 or 2, we skip this part and simply return the top left corner of the result matrix
            //which is one
            while (y > 0) {
                //If "binary" digit at lowest position is equal to 1
                if ((y % 2) == 1) {
                    //We multiply the "current" power of our matrix to the result
                    resultMatrix = matrixMulBig(resultMatrix, powerMatrix, 2, 2, 2, 2);
                }
                //Then we calculate the power for the next round
                powerMatrix = matrixMulBig(powerMatrix, powerMatrix, 2, 2, 2, 2);
                //Then divide y to "move down" a digit
                y = y / 2;
            }
        }
        //Return value at upper left hand corner of matrix
        return resultMatrix[0][0];
    }
}
