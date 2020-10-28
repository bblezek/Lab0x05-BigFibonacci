import com.company.MyBigInteger;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class MyBigIntegerTest {
    @Test
    public void testPlus() {
        MyBigInteger myBigIntegerOne = new MyBigInteger();
        MyBigInteger myBigIntegerTwo = new MyBigInteger();
        MyBigInteger myBigResult;
        long a, b;
        Random rand = new Random();
        for(int x = 0; x < 10; x++){
            a = rand.nextLong();
            b = rand.nextLong() % (Long.MAX_VALUE - a);
            myBigIntegerOne.setValue(Long.toString(a));;
            myBigIntegerTwo.setValue(Long.toString(b));
            myBigResult = myBigIntegerOne.MyBigIntegerPlus(myBigIntegerTwo);
            Assert.assertEquals(String.valueOf(a+b), myBigResult.Value());
        }
    }

    @Test
    public void testMinus(){
        MyBigInteger myBigIntegerOne = new MyBigInteger();
        MyBigInteger myBigIntegerTwo = new MyBigInteger();
        MyBigInteger myBigResult;
        long a, b;
        Random rand = new Random();
        for(int x = 0; x < 10; x++){
            a = rand.nextLong();
            b = rand.nextLong() % (Long.MAX_VALUE - a);
            myBigIntegerOne.setValue(Long.toString(a));
            myBigIntegerTwo.setValue(Long.toString(b));
            myBigResult = myBigIntegerOne.MyBigIntegerMinus(myBigIntegerTwo);
            Assert.assertEquals(String.valueOf(a-b), myBigResult.Value());
        }
    }

    @Test
    public void testTimes(){
        MyBigInteger myBigIntegerOne = new MyBigInteger();
        MyBigInteger myBigIntegerTwo = new MyBigInteger();
        MyBigInteger myBigResult;
        long a, b;
        Random rand = new Random();
        for(int x = 0; x < 10; x++){
            a = rand.nextInt();
            b = rand.nextInt();
            myBigIntegerOne.setValue(Long.toString(a));
            myBigIntegerTwo.setValue(Long.toString(b));
            myBigResult = myBigIntegerOne.MyBigIntegerTimes(myBigIntegerTwo);
            Assert.assertEquals(String.valueOf(a*b), myBigResult.Value());
        }
    }

}
