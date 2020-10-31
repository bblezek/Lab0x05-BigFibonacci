import com.company.BigFibonacci;
import com.company.MyBigInteger;
import com.company.Fibonacci;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

public class BigFibonacciTest {

    @Test
    public void fibMatrixBigTest() {
        System.out.printf("Fibonacci big matrix test: \n");
        MyBigInteger a = new MyBigInteger();
        MyBigInteger result = new MyBigInteger();
        BigFibonacci bigFib = new BigFibonacci();
        Fibonacci fib = new Fibonacci();
        long smallResult;
        for (int x = 0; x < 92; x++) {
            a.setValue(String.valueOf(x));
            result = bigFib.fibMatrixBig(a);
            smallResult = fib.fibMatrix(x);
            Assert.assertEquals(String.valueOf(smallResult), result.Value());
        }

        //Testing with a few random values calculated using this calculator:
        //https://www.omnicalculator.com/math/fibonacci
        String strResult = "453973694165307953197296969697410619233826";
        a.setValue("201");
        result = bigFib.fibMatrixBig(a);
        Assert.assertEquals(strResult, result.Value());
        System.out.printf("Actual: %s \n", strResult);
        System.out.printf("Expected: %s \n", result.Value());

        strResult = "155576970220531065681649693";
        a.setValue("127");
        result = bigFib.fibMatrixBig(a);
        Assert.assertEquals(strResult, result.Value());
        System.out.printf("Actual: %s \n", strResult);
        System.out.printf("Expected: %s \n", result.Value());

        strResult = "7896325826131730509282738943634332893686268675876375";
        a.setValue("250");
        result = bigFib.fibMatrixBig(a);
        Assert.assertEquals(strResult, result.Value());
        System.out.printf("Actual: %s \n", strResult);
        System.out.printf("Expected: %s \n", result.Value());

    }

    @Test
    public void fibLoopBigTest() {
        System.out.printf("Fibonacci big loop test: \n");
        MyBigInteger a = new MyBigInteger();
        MyBigInteger result = new MyBigInteger();
        BigFibonacci bigFib = new BigFibonacci();
        Fibonacci fib = new Fibonacci();
        long smallResult;
        a.setValue(String.valueOf("34"));
        result = bigFib.fibLoopBig(a);
        smallResult = fib.fibMatrix(34);
        Assert.assertEquals(String.valueOf(smallResult), result.Value());
        for (int x = 0; x < 92; x++) {
            a.setValue(String.valueOf(x));
            result = bigFib.fibLoopBig(a);
            smallResult = fib.fibMatrix(x);
            Assert.assertEquals(String.valueOf(smallResult), result.Value());
        }

        //Testing with a few random values calculated using this calculator:
        //https://www.omnicalculator.com/math/fibonacci
        String strResult = "453973694165307953197296969697410619233826";
        a.setValue("201");
        result = bigFib.fibLoopBig(a);
        Assert.assertEquals(strResult, result.Value());
        System.out.printf("Actual: %s \n", strResult);
        System.out.printf("Expected: %s \n", result.Value());

        strResult = "155576970220531065681649693";
        a.setValue("127");
        result = bigFib.fibLoopBig(a);
        Assert.assertEquals(strResult, result.Value());
        System.out.printf("Actual: %s \n", strResult);
        System.out.printf("Expected: %s \n", result.Value());

        strResult = "7896325826131730509282738943634332893686268675876375";
        a.setValue("250");
        result = bigFib.fibLoopBig(a);
        Assert.assertEquals(strResult, result.Value());
        System.out.printf("Actual: %s \n", strResult);
        System.out.printf("Expected: %s \n", result.Value());

    }
}
