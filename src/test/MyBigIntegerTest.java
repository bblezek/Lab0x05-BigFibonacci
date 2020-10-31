import com.company.MyBigInteger;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

public class MyBigIntegerTest {
    @Test
    public void testPlus() {
        MyBigInteger myBigIntegerOne = new MyBigInteger();
        MyBigInteger myBigIntegerTwo = new MyBigInteger();
        MyBigInteger myBigResult;
        long a = 1;
        long b = 1;
        int x;
        Random rand = new Random();
        for (x = 0; x < 1000; x++) {
            a = rand.nextLong();
            b = rand.nextLong() % (Long.MAX_VALUE - a);
            myBigIntegerOne.setValue(Long.toString(a));
            ;
            myBigIntegerTwo.setValue(Long.toString(b));
            myBigResult = myBigIntegerOne.MyBigIntegerPlus(myBigIntegerTwo);
            Assert.assertEquals(String.valueOf(a + b), myBigResult.Value());
        }

        BigInteger bigIntOne;
        BigInteger bigIntTwo;
        BigInteger bigIntResult;
        int len = 50;
        for (x = 0; x < 1000; x++) {
            bigIntOne = new BigInteger(len, rand);
            bigIntTwo = new BigInteger(len, rand);
            bigIntResult = bigIntOne.add(bigIntTwo);
            myBigIntegerOne.setValue(bigIntOne.toString());
            myBigIntegerTwo.setValue(bigIntTwo.toString());
            myBigResult = myBigIntegerOne.MyBigIntegerPlus(myBigIntegerTwo);
            if (x < 10) {
                System.out.printf("Expected: %s + %s = %s \n", bigIntOne.toString(), bigIntTwo.toString(), bigIntResult.toString());
                System.out.printf("Actual:   %s + %s = %s \n", myBigIntegerOne.Value(), myBigIntegerTwo.Value(), myBigResult.Value());
            }
        }
    }

    @Test
    public void testMinus() {
        MyBigInteger myBigIntegerOne = new MyBigInteger();
        MyBigInteger myBigIntegerTwo = new MyBigInteger();
        MyBigInteger myBigResult;
        long a, b;
        Random rand = new Random();
        for (int x = 0; x < 10; x++) {
            a = rand.nextInt();
            b = rand.nextInt();
            myBigIntegerOne.setValue(Long.toString(a));
            myBigIntegerTwo.setValue(Long.toString(b));
            myBigResult = myBigIntegerOne.MyBigIntegerMinus(myBigIntegerTwo);
            Assert.assertEquals(String.valueOf(a - b), myBigResult.Value());
            System.out.printf("%s - %s = %s\n", myBigIntegerOne.Value(), myBigIntegerTwo.Value(),
                    myBigResult.Value());
        }

        for (int x = 0; x < 1000; x++) {
            a = rand.nextLong();
            b = rand.nextLong() % (Long.MAX_VALUE - a);
            myBigIntegerOne.setValue(Long.toString(a));
            myBigIntegerTwo.setValue(Long.toString(b));
            myBigResult = myBigIntegerOne.MyBigIntegerMinus(myBigIntegerTwo);
            Assert.assertEquals(String.valueOf(a - b), myBigResult.Value());
        }
    }

    @Test
    public void testTimes() {
        MyBigInteger myBigIntegerOne = new MyBigInteger();
        MyBigInteger myBigIntegerTwo = new MyBigInteger();
        MyBigInteger myBigResult;
        long a, b;
        int x;
        Random rand = new Random();
        for (x = 0; x < 1000; x++) {
            a = rand.nextInt();
            b = rand.nextInt();
            myBigIntegerOne.setValue(Long.toString(a));
            myBigIntegerTwo.setValue(Long.toString(b));
            myBigResult = myBigIntegerOne.MyBigIntegerTimes(myBigIntegerTwo);
            Assert.assertEquals(String.valueOf(a * b), myBigResult.Value());
            if (x < 10) {
                System.out.printf("%s * %s = %s \n", myBigIntegerOne.Value(), myBigIntegerTwo.Value(), myBigResult.Value());
            }
        }

        BigInteger bigIntOne;
        BigInteger bigIntTwo;
        BigInteger bigIntResult;
        int len = 50;
        for (x = 0; x < 1000; x++) {
            bigIntOne = new BigInteger(len, rand);
            bigIntTwo = new BigInteger(len, rand);
            bigIntResult = bigIntOne.multiply(bigIntTwo);
            myBigIntegerOne.setValue(bigIntOne.toString());
            myBigIntegerTwo.setValue(bigIntTwo.toString());
            myBigResult = myBigIntegerOne.MyBigIntegerTimes(myBigIntegerTwo);
            if (x < 10) {
                System.out.printf("Expected: %s * %s = %s \n", bigIntOne.toString(), bigIntTwo.toString(), bigIntResult.toString());
                System.out.printf("Actual:   %s * %s = %s \n", myBigIntegerOne.Value(), myBigIntegerTwo.Value(), myBigResult.Value());
            }
            if (!myBigResult.Value().equals(bigIntResult.toString())) {
                System.out.printf("Big int and my big int results don't match!");
            }
        }
    }

    @Test
    public void testFasterTimes() {
        MyBigInteger myBigIntegerOne = new MyBigInteger();
        MyBigInteger myBigIntegerTwo = new MyBigInteger();
        MyBigInteger myBigResult;
        long a, b;
        Random rand = new Random();
        int x;
        for (x = 0; x < 1000; x++) {
            a = rand.nextInt();
            b = rand.nextInt();
            myBigIntegerOne.setValue(Long.toString(a));
            myBigIntegerTwo.setValue(Long.toString(b));
            myBigResult = myBigIntegerOne.MyBigIntegerFasterTimes(myBigIntegerTwo);
            Assert.assertEquals(String.valueOf(a * b), myBigResult.Value());
            if (x < 10) {
                System.out.printf("%s * %s = %s \n", myBigIntegerOne.Value(), myBigIntegerTwo.Value(), myBigResult.Value());
            }
        }

        BigInteger bigIntOne;
        BigInteger bigIntTwo;
        BigInteger bigIntResult;
        int len = 50;
        for (x = 0; x < 1000; x++) {
            bigIntOne = new BigInteger(len, rand);
            bigIntTwo = new BigInteger(len, rand);
            bigIntResult = bigIntOne.add(bigIntTwo);
            myBigIntegerOne.setValue(bigIntOne.toString());
            myBigIntegerTwo.setValue(bigIntTwo.toString());
            myBigResult = myBigIntegerOne.MyBigIntegerPlus(myBigIntegerTwo);
            if (x < 10) {
                System.out.printf("Expected: %s * %s = %s \n", bigIntOne.toString(), bigIntTwo.toString(), bigIntResult.toString());
                System.out.printf("Actual:   %s * %s = %s \n", myBigIntegerOne.Value(), myBigIntegerTwo.Value(), myBigResult.Value());
            }
            if (!myBigResult.Value().equals(bigIntResult.toString())) {
                System.out.printf("Big int and my big int results don't match!");
            }
        }
    }

}
