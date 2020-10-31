package com.company;

public class MyBigInteger {

    //Defining property "value" with value "sign"
    private String Value;
    private Boolean isNegative;

    public MyBigInteger() {
        Value = new String();
        isNegative = false;
    }

    //Constructor that takes a string
    public MyBigInteger(String stringValue) {
        Value = stringValue;
        if (stringValue.charAt(0) == '-') {
            isNegative = true;
            Value = Value.substring(1);
        } else {
            isNegative = false;
        }
    }

    //Sets 'Value' for MyBigInteger
    //Checks for negative numbers and initializes Value and isNegative appropriately
    public void setValue(String string) {
        if (string.charAt(0) == '-') {
            isNegative = true;
            Value = string.substring(1);
        } else {
            isNegative = false;
            Value = string;
        }
    }

    //Takes a character and converts it to an int
    private int convertToInt(char c) {
        return c - 48;
    }

    //Takes an integer and returns the equivalent character
    private char convertToChar(int i) {
        return (char) (i + 48);
    }

    //Add leading zeros to array
    private String addZeros(String str, int numToAdd) {
        char[] padding = new char[numToAdd];
        int idx;
        for (idx = 0; idx < numToAdd; idx++) {
            padding[idx] = '0';
        }
        return String.valueOf(padding) + str;
    }

    //Removes leading zeros from array of numbers
    private static String removeLeadingZeros(char[] array) {
        int leadingZeros = 0;
        for (leadingZeros = 0; leadingZeros < array.length - 1; leadingZeros++) {
            if (array[leadingZeros] != '0') {
                break;
            }
        }
        return String.valueOf(array).substring(leadingZeros);
    }

    //Removes leading zeros from string
    private static String removeLeadingZeros(String str) {
        int leadingZeros = 0;
        for (leadingZeros = 0; leadingZeros < str.length() - 1; leadingZeros++) {
            if (str.charAt(leadingZeros) != '0') {
                break;
            }
        }
        return str.substring(leadingZeros);
    }

    //Adds two large integers
    public MyBigInteger MyBigIntegerPlus(MyBigInteger x) {
        //Stores result
        MyBigInteger result = new MyBigInteger();
        //If 'this' is negative, subtract this from x
        if (this.isNegative && !x.isNegative) {
            this.isNegative = false;
            result = x.MyBigIntegerMinus(this);
            //If 'x' is negative, subtract x from this
        } else if (x.isNegative && !this.isNegative) {
            x.isNegative = false;
            result = this.MyBigIntegerMinus(x);
        } else {
            result.Value = StringAdd(this.Value, x.Value);
            if (this.isNegative && x.isNegative) {
                result.isNegative = true;
            }
        }
        return result;
    }

    //Subtracts x from 'this'
    public MyBigInteger MyBigIntegerMinus(MyBigInteger x) {
        MyBigInteger result = new MyBigInteger();

        //Depending on the sign, calls StringMinus or StringAdd
        //Determine whether the numbers are negative or positive
        if (x.isNegative && this.isNegative) {
            result.Value = StringMinus(x.Value, this.Value);
            //If 'this' is negative
        } else if (this.isNegative) {
            //Add values as negative
            result.Value = StringAdd(this.Value, x.Value);
            result.isNegative = true;
            //If 'x' is negative
        } else if (x.isNegative) {
            //Add values as both positive
            result.Value = StringAdd(this.Value, x.Value);
            //If we have two positive values
        } else {
            result.Value = StringMinus(this.Value, x.Value);
        }
        if (result.Value.charAt(0) == '-') {
            result.Value = result.Value.substring(1);
            result.isNegative = !result.isNegative;
        }
        return result;
    }

    //Slower 'MyBigInteger' multiplication
    public MyBigInteger MyBigIntegerTimes(MyBigInteger x) {
        MyBigInteger result = new MyBigInteger();
        //Calls 'grade-school' multiplication algorithm on this.Value and x.Value
        result.Value = removeLeadingZeros(StringTimes(this.Value, x.Value));

        //Checking to see if the result should be 0
        if (this.isNegative && !x.isNegative) {
            result.isNegative = true;
        } else if (!this.isNegative && x.isNegative) {
            result.isNegative = true;
        }

        return result;
    }

    //Adds two strings
    private String StringAdd(String a, String b) {
        //Padding strings as necessary
        if (a.length() > b.length()) {
            b = addZeros(b, a.length() - b.length());
        } else if (b.length() > a.length()) {
            a = addZeros(a, b.length() - a.length());
        }

        char[] result = new char[a.length() + 1];
        int carryOver = 0;
        int digitResult = 0;
        //Looping through both arrays and adding
        for (int idx = a.length() - 1; idx >= 0; idx--) {
            digitResult = convertToInt(a.charAt(idx)) + convertToInt(b.charAt(idx)) + carryOver;
            carryOver = 0;
            //If result 'overflows' digit
            if (digitResult >= 10) {
                carryOver = 1;
                digitResult = digitResult % 10;
            }
            result[idx + 1] = convertToChar(digitResult);
        }
        //If we have an 'extra' 1, put in first digit
        if (carryOver == 1) {
            result[0] = '1';
        } else {
            return String.valueOf(result).substring(1);
        }
        return String.valueOf(result);
    }

    //Subtract one string from another
    private String StringMinus(String A, String B) {
        //If there is nothing in A
        if (A.length() == 0 && !B.equals("0")) {
            return "-" + B;
        //If there is nothing in A and B is 0, return 0
        } else if (A.length() == 0 && B.equals("0")) {
            return B;
        //If A and B are equal
        } else if (A.equals(B)) {
            return "0";
        //If B has nothing in it
        } else if (B.length() == 0) {
            return A;
        //Pad A or B as necessary
        } else if (A.length() > B.length()) {
            B = addZeros(B, A.length() - B.length());
        } else if (B.length() > A.length()) {
            A = addZeros(A, B.length() - A.length());
            return "-" + StringMinus(B, A);
        }

        int idx, digitResult, carryOver;
        //Stores result
        char[] result = new char[A.length()];
        //Stores negative number if B 'overflows' A
        char[] negative = new char[A.length()];
        for (idx = 0; idx < A.length(); idx++) {
            result[idx] = '0';
            negative[idx] = '0';
        }

        carryOver = 0;
        //Loop through both arrays
        for (idx = A.length() - 1; idx >= 0; idx--) {
            digitResult = convertToInt(A.charAt(idx)) - convertToInt(B.charAt(idx)) - carryOver;
            carryOver = 0;
            //If we are down to our last element and the number becomes negative
            if (digitResult < 0 && idx == 0) {
                negative[idx] = convertToChar(digitResult * -1);
                return "-" + StringMinus(String.valueOf(negative), String.valueOf(result));
                //If there is a digit to carry
            } else if (digitResult < 0) {
                carryOver = 1;
                digitResult = digitResult + 10;
                result[idx] = convertToChar(digitResult);
            } else {
                result[idx] = convertToChar(digitResult);
            }
        }
        return removeLeadingZeros(result);
    }

    //Multiplies two strings the grade-school or "slow" way
    private String StringTimes(String A, String B) {
        int aDigit, bDigit, totalADigits, totalBDigits, totalDigits, carryOver, digitResult, bMul;
        int[][] results;
        char[] totalResult;
        totalADigits = A.length();
        totalBDigits = B.length();
        totalDigits = totalADigits + totalBDigits;
        carryOver = 0;

        //Holds result from each iteration
        results = new int[totalBDigits][totalDigits];
        //Holds final result
        totalResult = new char[totalDigits];

        int r, c;
        //Filling result array with 0s
        for (r = 0; r < totalBDigits; r++) {
            for (c = 0; c < totalADigits; c++) {
                results[r][c] = 0;
            }
        }

        //Take lowest bDigit and work up
        for (bDigit = totalBDigits - 1; bDigit >= 0; bDigit--) {
            bMul = convertToInt(B.charAt(bDigit));
            //Multiply by each digit of A
            for (aDigit = totalADigits - 1; aDigit >= 0; aDigit--) {
                digitResult = bMul * convertToInt(A.charAt(aDigit)) + carryOver;
                carryOver = 0;
                //Figuring out how much to carry to next digit
                if (digitResult >= 10) {
                    carryOver = digitResult / 10;
                    digitResult = digitResult % 10;
                }
                //Adding result to results array
                results[bDigit][aDigit + bDigit + 1] = digitResult;
            }
            //Adding any overflow from the final multiplication to the appropriate high digit spot in 'results'
            if (carryOver > 0) {
                results[bDigit][bDigit] = carryOver;
                carryOver = 0;
            }
        }

        int totalIdx = totalDigits - 1;
        carryOver = 0;
        //Calculating totals for each digit place
        for (c = totalDigits - 1; c >= 0; c--) {
            digitResult = carryOver;
            for (r = totalBDigits - 1; r >= 0; r--) {
                digitResult += results[r][c];
                carryOver = 0;
            }
            if (digitResult >= 10) {
                carryOver = digitResult / 10;
                digitResult = digitResult % 10;
            }
            totalResult[totalIdx] = convertToChar(digitResult);
            totalIdx--;
        }
        return String.valueOf(totalResult);
    }

    //Multiplies two strings recursively
    private String FasterTimesHelper(String A, String B) {
        A = removeLeadingZeros(A);
        B = removeLeadingZeros(B);

        int returnVal;
        //If A and B are single digit numbers
        if (A.length() == 1 && B.length() == 1) {
            returnVal = convertToInt(A.charAt(0)) * convertToInt(B.charAt(0));
            return String.valueOf(returnVal);
        }

        //Checking to make sure A and B are same length
        //If not, pad with zeros
        int halfDigits;
        if (A.length() > B.length()) {
            B = addZeros(B, A.length() - B.length());
        } else if (B.length() > A.length()) {
            A = addZeros(A, B.length() - A.length());
        }

        //If A and B are not divisible by 2, add a leading zero to each
        if (A.length() % 2 != 0) {
            A = "0" + A;
            B = "0" + B;
        }

        halfDigits = A.length() / 2;

        //Splitting strings into low and high
        String lowA, lowB, highA, highB;
        highA = A.substring(0, halfDigits);
        highB = B.substring(0, halfDigits);
        lowA = A.substring(halfDigits);
        lowB = B.substring(halfDigits);

        String highMul, lowMul, addA, addB, middleDigits;
        //Getting high digits of final result
        highMul = FasterTimesHelper(highA, highB);
        //Getting low digits of final result
        lowMul = FasterTimesHelper(lowA, lowB);
        addA = StringAdd(lowA, highA);
        addB = StringAdd(lowB, highB);
        //Getting "middle" digits - what will go in 10^halfDigit..10^fullDigit
        middleDigits = FasterTimesHelper(addA, addB);
        //StringMinus doesn't work with leading zeros
        middleDigits = StringMinus(middleDigits, removeLeadingZeros(highMul));
        middleDigits = StringMinus(middleDigits, removeLeadingZeros(lowMul));

        String result, partialResult;
        String carryOver = "";
        //Add lower digits to final result
        //If we have more digits in 'lower digits' than we have room for
        if (lowMul.length() > halfDigits) {
            carryOver = lowMul.substring(0, lowMul.length() - halfDigits);
            result = lowMul.substring(lowMul.length() - halfDigits);
            //If there are less digits in 'lower digits' than there are supposed to be
        } else if (lowMul.length() < halfDigits) {
            //pad with zeros
            lowMul = addZeros(lowMul, halfDigits - lowMul.length());
            result = lowMul;
        } else {
            result = lowMul;
        }

        //Add 'middle digits' to final result
        //Adding 'extra' lower digits to 'middle digits'
        partialResult = StringAdd(middleDigits, carryOver);
        carryOver = "";
        if (partialResult.length() > halfDigits) {
            carryOver = partialResult.substring(0, partialResult.length() - halfDigits);
            result = partialResult.substring(partialResult.length() - halfDigits) + result;
        //Adding extra digits to 'middle digits' if we don't have enough
        } else if (partialResult.length() < halfDigits) {
            partialResult = addZeros(partialResult, halfDigits - partialResult.length());
            result = partialResult + result;
        } else {
            result = partialResult + result;
        }

        //Adding high digits to result
        //Adding 'extra' middle digits to 'high digits'
        if (!carryOver.equals("")) {
            partialResult = StringAdd(highMul, carryOver);
            result = partialResult + result;
        } else {
            result = highMul + result;
        }

        return result;
    }

    //Simply calls FasterTimesHelper recursive function on this.Value and x.Value
    public MyBigInteger MyBigIntegerFasterTimes(MyBigInteger x) {

        String resultString = FasterTimesHelper(this.Value, x.Value);
        //Remove leading zeros from result
        MyBigInteger result = new MyBigInteger(removeLeadingZeros(resultString));
        //Checking if the result should be negative
        if (this.isNegative && !x.isNegative && !result.Value.equals("0")) {
            result.isNegative = true;
        } else if (!this.isNegative && x.isNegative && !result.Value.equals("0")) {
            result.isNegative = true;
        }
        return result;
    }

    public Boolean MyBigIntegerLessThanEqual(MyBigInteger x){
        if(this.Value().length() > x.Value().length()){
            return false;
        } else if(x.Value().length() > this.Value.length()){
            return true;
        }
        for(int digits = 0; digits < this.Value().length(); digits++) {
            if(this.Value.charAt(digits) > x.Value().charAt(digits)){
                return false;
            } else if(x.Value.charAt(digits) > this.Value().charAt(digits)){
                return true;
            }
        }
        return true;
    }

    public Boolean MyBigIntegerGreaterThan(MyBigInteger x){
        if(this.Value().length() > x.Value().length()){
            return true;
        } else if(x.Value().length() > this.Value.length()){
            return false;
        }
        for(int digits = 0; digits < this.Value().length(); digits++) {
            if(this.Value.charAt(digits) > x.Value().charAt(digits)){
                return true;
            } else if(x.Value.charAt(digits) > this.Value().charAt(digits)){
                return false;
            }
        }
        return false;
    }

    //Returns a string representation of Value
    public String Value() {
        if (isNegative) {
            return '-' + Value;
        }
        return Value;
    }

    //Returns an abridged representation of "Value"
    public String AbbreviatedValue() {
        int length = Value.length();
        if (isNegative && Value.length() <= 12) {
            return '-' + Value;
        } else if (isNegative){
            return '-' + Value.substring(0, 4) + "..." + Value.substring(length - 5, length);
        } else if(Value.length() <= 12){
            return Value;
        }

        return Value.substring(0, 4) + "..." + Value.substring(length - 5, length);
    }
}
