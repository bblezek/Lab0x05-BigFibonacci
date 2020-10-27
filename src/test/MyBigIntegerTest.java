import com.company.MyBigInteger;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class MyBigIntegerTest {
    @Test
    public void testPlus() throws Exception {
        MyBigInteger myBigIntegerOne = new MyBigInteger();
        MyBigInteger myBigIntegerTwo = new MyBigInteger();
        MyBigInteger myBigResult = new MyBigInteger();
        long a, b;
        String result;
        Random rand = new Random();
        for(int x = 0; x < 10; x++){
            a = rand.nextLong();
            b = rand.nextLong() % (Long.MAX_VALUE - a);
            myBigIntegerOne.Value = Long.toString(a);
            myBigIntegerTwo.Value = Long.toString(b);
            myBigResult = myBigIntegerOne.MyBigIntegerPlus(myBigIntegerTwo);
            result = String.valueOf(a+b);
            //System.out.printf("%d \n", Long.parseLong(myBigResult.Value));
            //System.out.printf("%d \n", a+b);
            Assert.assertEquals(String.valueOf(a+b), myBigResult.Value);
        }
    }

}
