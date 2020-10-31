import com.company.BigFibonacci;
import com.company.MyBigInteger;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

public class BigFibonacciTest {

    @Test
    public void matrixMulTest(){
        MyBigInteger a = new MyBigInteger();
        MyBigInteger result = new MyBigInteger();
        BigFibonacci bigFib = new BigFibonacci();
        Fibonacci fib = new Fibonacci();
        long smallResult;
        for(int x = 0; x < 92; x++){
            a.setValue(String.valueOf(x));
            result = bigFib.fibMatrixBig(a);
            smallResult = fib.fibMatrix(x);
            Assert.assertEquals(String.valueOf(smallResult), result.Value());
        }
    }
}
