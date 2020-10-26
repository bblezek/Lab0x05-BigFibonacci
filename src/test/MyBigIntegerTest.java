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
            myBigIntegerOne.Value = String.valueOf(a);
            myBigIntegerTwo.Value = String.valueOf(b);
            myBigResult = myBigIntegerOne.MyBigIntegerPlus(myBigIntegerTwo);
            result = String.valueOf(a+b);
            Assert.assertSame(a+b, Long.parseLong(myBigResult.Value));
        }
    }

}
