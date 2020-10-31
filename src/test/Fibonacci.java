public class Fibonacci {

    //Matrix multiplication function
    public static long[][] matrixMul(long[][] matrixOne, long[][] matrixTwo, int oneRows, int oneCols, int twoRows, int twoCols) {
        //Creating a new matrix to store our results
        long[][] resultMatrix = new long[oneRows][twoCols];
        long result;
        //Ensuring that we can multiply the matrix
        if (oneCols != twoRows) {
            System.out.printf("Incorrect matrix arrays!! \n");
            return resultMatrix;
        }

        //Select row of matrix one
        for (int oneRow = 0; oneRow < oneRows; oneRow++) {
            //Select column of matrix two
            for (int twoCol = 0; twoCol < twoCols; twoCol++) {
                result = 0;
                //Iterate over row and column, adding to result
                for (int sharedDim = 0; sharedDim < oneCols; sharedDim++) {
                    result = result + (matrixOne[oneRow][sharedDim] * matrixTwo[sharedDim][twoCol]);
                }
                //Setting spot in result matrix equal to result
                resultMatrix[oneRow][twoCol] = result;
            }
        }
        return resultMatrix;
    }

    //Fibonacci sequence function based on matrices
    public static long fibMatrix(long x) {
        //This matrix will store our "powers" as we calculate them
        long[][] powerMatrix = {{1, 1},
                {1, 0}};
        //This matrix will store our final result matrix
        long[][] resultMatrix = {{1, 1},
                {1, 0}};

        //We only need to iterate to matrix "power" number - 2
        //This is because the first value of the sequence is 0 and the second value is 1, so we can't technically
        //perform a matrix multiplication for those two "powers" and thus the sequence of matrix powers starts at
        //index 3 with "M to the first"
        long y = x - 2;
        //Return 0 if number equals 0
        if (x == 0) {
            return x;
            //Otherwise, iterate over "binary digits" of num to find result matrix
        } else {
            //If x is index 1 or 2, we skip this part and simply return the top left corner of the result matrix
            //which is one
            while (y > 0) {
                //If "binary" digit at lowest position is equal to 1
                if ((y % 2) == 1) {
                    //We multiply the "current" power of our matrix to the result
                    resultMatrix = matrixMul(resultMatrix, powerMatrix, 2, 2, 2, 2);
                }
                //Then we calculate the power for the next round
                powerMatrix = matrixMul(powerMatrix, powerMatrix, 2, 2, 2, 2);
                //Then divide y to "move down" a digit
                y = y / 2;
            }
        }
        //Return value at upper left hand corner of matrix
        return resultMatrix[0][0];
    }
}
