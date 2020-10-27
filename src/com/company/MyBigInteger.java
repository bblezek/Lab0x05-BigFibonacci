package com.company;

public class MyBigInteger {

    //Defining property "value" with value "sign"
    public String Value;
    Boolean positive;

    public MyBigInteger(){
        Value = new String();
        positive = true;
    }

    //Constructor that takes a string
    public MyBigInteger(String stringValue){
        Value = stringValue;
        if(stringValue.charAt(0) == '-'){
            positive = false;
        } else {
            positive = true;
        }
    }

    private int convertToInt(char c){
        return c - 48;
    }

    private char convertToChar(int i){
        return (char) (i+48);
    }

    private static String formatString(char[] array){
        int leadingZeros = 0;
        String string = new String();
        for(leadingZeros = 0; leadingZeros < array.length; leadingZeros++){
            if(array[leadingZeros] != '0'){
                break;
            }
        }
        return String.valueOf(array).substring(leadingZeros);
    }

    public MyBigInteger MyBigIntegerPlus(MyBigInteger x) {
        //StringBuilder resultString = new StringBuilder();
        MyBigInteger result = new MyBigInteger();
        char[] resultString, padding;
        int carryOver, digitResult, operandDigit, digitsAdded, idx;
        carryOver = 0;

        //If both numbers are negative
        if(x.Value.charAt(0) == '-' && this.Value.charAt(0) == '-'){
            x.Value = x.Value.substring(1);
            this.Value = this.Value.substring(1);
            result = this.MyBigIntegerPlus(x);
            result.Value = '-' + result.Value;
        //If 'this' is negative
        } else if(this.Value.charAt(0) == '-'){
            this.Value = this.Value.substring(1);
            result = x.MyBigIntegerMinus(this);
        } else if(x.Value.charAt(0) == '-'){
            x.Value = x.Value.substring(1);
            result = this.MyBigIntegerMinus(x);
        } else {
            //If 'x' is longer than 'this'
            if (x.Value.length() > this.Value.length()) {
                operandDigit = x.Value.length() - 1;
                digitsAdded = x.Value.length() - this.Value.length();
                padding = new char[digitsAdded];
                for(idx = 0; idx < digitsAdded; idx++){
                    padding[idx] = '0';
                }
                this.Value = String.valueOf(padding) + this.Value;
            } else if(this.Value.length() > x.Value.length()){
                operandDigit = this.Value.length() - 1;
                digitsAdded = this.Value.length() - x.Value.length();
                padding = new char[digitsAdded];
                for(idx = 0; idx < digitsAdded; idx++){
                    padding[idx] = '0';
                }
                x.Value = String.valueOf(padding) + x.Value;
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
            if(carryOver == 1){
                resultString[0] = '1';
                result.Value = formatString(resultString);
            } else {
                resultString[0] = '0';
                //result.Value = resultString.toString().substring(findLeadingZeros(resultString));
                result.Value = formatString(resultString);
            }
        }
        return result;
    }

    //Assume numbers won't be marked with a '+'
    public MyBigInteger MyBigIntegerMinus(MyBigInteger x){
        MyBigInteger result = new MyBigInteger();
        //Records whether 'result' is negative
        boolean resultIsNegative = false;
        char[] resultString, padding, negativeValue;
        int carryOver, digitResult, operandDigit, resultDigit,idx;
        int thisDigitsToAdd = 0;
        int xDigitsToAdd = 0;
        long negativeResult = 0;
        carryOver = 0;

        //Determine whether the numbers are negative or positive
        //If both numbers are negative
        if(this.Value.charAt(0) == '-' && x.Value.charAt(0) == '-'){
            //Remove negative sign from x
            x.Value = x.Value.substring(1);
            //Remove negative sign from 'this' because it will be subtracted
            //from x
            this.Value = this.Value.substring(1);
            result = x.MyBigIntegerMinus(this);
            result.Value = '-' + result.Value;
        //If 'this' is negative
        } else if(this.Value.charAt(0)== '-'){
            //Add values as negative
            x.Value = '-' + x.Value;
            result = this.MyBigIntegerPlus(x);
        //If 'x' is negative
        } else if(x.Value.charAt(0) == '-'){
            //Add values as both positive
            x.Value = x.Value.substring(1);
            result = this.MyBigIntegerPlus(x);
        } else {

            //If x.Value is longer than this.Value
            if (x.Value.length() > this.Value.length()) {
                //Add padding to 'this' value
                thisDigitsToAdd = x.Value.length() - this.Value.length();
                padding = new char[thisDigitsToAdd];
                for (idx = 0; idx < thisDigitsToAdd; idx++) {
                    padding[idx] = '0';
                }
                this.Value = String.valueOf(padding) + this.Value;
                operandDigit = x.Value.length() - 1;
            } else if(this.Value.length() > x.Value.length()){
                xDigitsToAdd = this.Value.length() - x.Value.length();
                padding = new char[xDigitsToAdd];
                for (idx = 0; idx < xDigitsToAdd; idx++) {
                    padding[idx] = '0';
                }
                x.Value = String.valueOf(padding) + x.Value;
                operandDigit = this.Value.length() - 1;
            } else {
                operandDigit = this.Value.length() - 1;
            }

            resultString = new char[operandDigit + 1];
            negativeValue = new char[operandDigit + 1];
            resultDigit = operandDigit;

            for(idx = 0; idx < operandDigit+1; idx++){
                negativeValue[idx] = '0';
                resultString[idx] = '0';
            }

            //Loop through both arrays
            for (idx = operandDigit; idx >= 0; idx--) {
                digitResult = convertToInt(this.Value.charAt(idx)) - convertToInt(x.Value.charAt(idx)) - carryOver;
                carryOver = 0;
                if(digitResult < 0 && idx == 0) {
                    negativeValue[idx] = convertToChar(digitResult*-1);
                    resultIsNegative = true;
                    //If we end up with a negative number and we are down to the 0s we added
                } else if (digitResult < 0 && idx < thisDigitsToAdd) {
                    negativeValue[idx] = convertToChar(digitResult * -1);
                    resultIsNegative = true;
                    //If there isn't a digit to carry
                } else if (digitResult < 0) {
                    carryOver = 1;
                    digitResult = digitResult + 10;
                    resultString[idx] = convertToChar(digitResult);
                } else {
                    resultString[idx] = convertToChar(digitResult);
                }
            }

            //If the number is negative, add a negative sign to the beginning
            if (resultIsNegative) {
                //Subtract result from negative result to get result
                carryOver = 0;
                for (idx = resultDigit; idx >= 0; idx--) {
                    digitResult = convertToInt(negativeValue[idx]) - convertToInt(resultString[idx]) - carryOver;
                    carryOver = 0;
                    if(digitResult < 0){
                        digitResult = digitResult + 10;
                        carryOver = 1;
                    }
                   resultString[idx] = convertToChar(digitResult);
                }
                result.Value = '-' + formatString(resultString);
                //result.Value = '-' + String.valueOf(resultString).substring(formatString(resultString));
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

    public String ToString(){
        return Value;
    }

}
