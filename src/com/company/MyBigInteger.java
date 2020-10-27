package com.company;

public class MyBigInteger {

    //Defining property "value" with value "sign"
    private String Value;
    private Boolean isNegative;

    public MyBigInteger(){
        Value = new String();
        isNegative = false;
    }

    //Constructor that takes a string
    public MyBigInteger(String stringValue){
        Value = stringValue;
        if(stringValue.charAt(0) == '-'){
            isNegative = true;
            Value = Value.substring(1);
        } else {
            isNegative = false;
        }
    }

    public void setValue(String string) {
        if(string.charAt(0) == '-'){
            isNegative = true;
            Value = string.substring(1);
        } else {
            isNegative = false;
            Value = string;
        }
    }

    //Takes a character and converts it to an int
    private int convertToInt(char c){
        return c - 48;
    }

    //Takes an integer and returns the equivalent character
    private char convertToChar(int i){
        return (char) (i+48);
    }

    //Removes leading zeros from array of numbers
    private static String formatString(char[] array){
        int leadingZeros = 0;
        for(leadingZeros = 0; leadingZeros < array.length; leadingZeros++){
            if(array[leadingZeros] != '0'){
                break;
            }
        }
        return String.valueOf(array).substring(leadingZeros);
    }

    //Adds two large integers
    public MyBigInteger MyBigIntegerPlus(MyBigInteger x) {
        //Stores result
        MyBigInteger result = new MyBigInteger();
        char[] resultString, padding;
        int carryOver, digitResult, operandDigit, digitsAdded, idx;
        carryOver = 0;

        //If 'this' is negative, subtract this from x
        if(this.isNegative && !x.isNegative){
            this.isNegative = false;
            result = x.MyBigIntegerMinus(this);
        //If 'x' is negative, subtract x from this
        } else if(x.isNegative && !this.isNegative){
            x.isNegative = false;
            result = this.MyBigIntegerMinus(x);
        } else {
            //If 'x' is longer than 'this'
            if (x.Value.length() > this.Value.length()) {
                //Add 0s to 'this'
                operandDigit = x.Value.length() - 1;
                digitsAdded = x.Value.length() - this.Value.length();
                padding = new char[digitsAdded];
                for(idx = 0; idx < digitsAdded; idx++){
                    padding[idx] = '0';
                }
                this.Value = String.valueOf(padding) + this.Value;
            //If 'this' is longer than 'x'
            } else if(this.Value.length() > x.Value.length()){
                //Adding leading 0s to 'x'
                operandDigit = this.Value.length() - 1;
                digitsAdded = this.Value.length() - x.Value.length();
                padding = new char[digitsAdded];
                for(idx = 0; idx < digitsAdded; idx++){
                    padding[idx] = '0';
                }
                x.Value = String.valueOf(padding) + x.Value;
            //Otherwise, just set the number of digit operands
            } else {
                operandDigit = this.Value.length() - 1;
                digitsAdded = 0;
            }
            resultString = new char[operandDigit + 2];

            //Process both arrays
            for(idx = operandDigit; idx >= 0; idx--) {
                //Add
                digitResult = convertToInt(x.Value.charAt(idx)) + convertToInt(this.Value.charAt(idx)) + carryOver;
                //Set overflow or "carry over" back to 0
                carryOver = 0;
                //Decide whether we have "overflow" in this digit
                if (digitResult >= 10) {
                    //If so, calculate overflow and current digit
                    carryOver = 1;
                    digitResult = digitResult - 10;
                }
                //Append result to our result string
                resultString[idx+1] = convertToChar(digitResult);
            }
            //If we end up with a last digit of carryover
            if(carryOver == 1){
                //Put a '1' in the highest digit
                resultString[0] = '1';
                result.Value = formatString(resultString);
            //Otherwise just set it to 0
                //which will then be removed when we call formatString
            } else {
                resultString[0] = '0';
                result.Value = formatString(resultString);
            }
            if(this.isNegative && x.isNegative){
                result.isNegative = true;
            }
        }
        return result;
    }

    //Subtracts x from 'this'
    public MyBigInteger MyBigIntegerMinus(MyBigInteger x){
        MyBigInteger result = new MyBigInteger();
        char[] resultString, padding, negativeValue;
        int carryOver, digitResult, operandDigit, idx;
        int thisDigitsToAdd = 0;
        int xDigitsToAdd = 0;
        carryOver = 0;

        //Determine whether the numbers are negative or positive
        //If both numbers are negative
        if(this.isNegative && x.isNegative){
            x.isNegative = false;
            this.isNegative = false;
            result = x.MyBigIntegerMinus(this);
            x.isNegative = true;
            this.isNegative = false;
        //If 'this' is negative
        } else if(this.isNegative){
            //Add values as negative
            x.isNegative = true;
            result = this.MyBigIntegerPlus(x);
            x.isNegative = false;
        //If 'x' is negative
        } else if(x.isNegative){
            //Add values as both positive
            x.isNegative = false;
            result = this.MyBigIntegerPlus(x);
            x.isNegative = true;
        //If we have two positive values
        } else {
            //If x.Value is longer than this.Value
            if (x.Value.length() > this.Value.length()) {
                //Add padding to 'this' value
                thisDigitsToAdd = x.Value.length() - this.Value.length();
                padding = new char[thisDigitsToAdd];
                for (idx = 0; idx < thisDigitsToAdd; idx++) {
                    padding[idx] = '0';
                }
                //Set number of digits in operand equal to x.Value
                this.Value = String.valueOf(padding) + this.Value;
                operandDigit = x.Value.length() - 1;
            //If this.Value is longer than x.value
            } else if(this.Value.length() > x.Value.length()){
                //Add padding
                xDigitsToAdd = this.Value.length() - x.Value.length();
                padding = new char[xDigitsToAdd];
                for (idx = 0; idx < xDigitsToAdd; idx++) {
                    padding[idx] = '0';
                }
                //Set number of digits in operand equal to this.Value
                x.Value = String.valueOf(padding) + x.Value;
                operandDigit = this.Value.length() - 1;
            //If they are the same length
            } else {
                operandDigit = this.Value.length() - 1;
            }

            resultString = new char[operandDigit + 1];
            negativeValue = new char[operandDigit + 1];

            //Filling result and negative arrays with 0s
            for(idx = 0; idx < operandDigit+1; idx++){
                negativeValue[idx] = '0';
                resultString[idx] = '0';
            }

            //Loop through both arrays
            for (idx = operandDigit; idx >= 0; idx--) {
                digitResult = convertToInt(this.Value.charAt(idx)) - convertToInt(x.Value.charAt(idx)) - carryOver;
                carryOver = 0;
                //If we are down to our last element and the number becomes negative
                if(digitResult < 0 && idx == 0) {
                    negativeValue[idx] = convertToChar(digitResult*-1);
                    result.isNegative = true;
                    //If we end up with a negative number and we are down to the 0s we added
                } else if (digitResult < 0 && idx < thisDigitsToAdd) {
                    //Create a negative value array
                    negativeValue[idx] = convertToChar(digitResult * -1);
                    result.isNegative = true;
                    //If there is a digit to carry
                } else if (digitResult < 0) {
                    carryOver = 1;
                    digitResult = digitResult + 10;
                    resultString[idx] = convertToChar(digitResult);
                } else {
                    resultString[idx] = convertToChar(digitResult);
                }
            }

            //If the number is negative
            if (result.isNegative) {
                //Subtract result from negative result to get result
                carryOver = 0;
                for (idx = operandDigit; idx >= 0; idx--) {
                    digitResult = convertToInt(negativeValue[idx]) - convertToInt(resultString[idx]) - carryOver;
                    carryOver = 0;
                    if(digitResult < 0){
                        digitResult = digitResult + 10;
                        carryOver = 1;
                    }
                   resultString[idx] = convertToChar(digitResult);
                }
                result.Value = formatString(resultString);
            //If the number is positive
            } else {
                result.Value = formatString(resultString);
            }
        }
        return result;
    }

    public MyBigInteger MyBigIntegersTimes(){
        MyBigInteger result = new MyBigInteger();
        return result;
    }

    public String Value(){
        if(isNegative) {
            return '-' + Value;
        }
        return Value;
    }

}
