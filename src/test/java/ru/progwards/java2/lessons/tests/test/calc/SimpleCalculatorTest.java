package ru.progwards.java2.lessons.tests.test.calc;

import org.junit.*;
import ru.progwards.java2.lessons.tests.SimpleCalculator;

import static org.junit.Assert.*;

public class SimpleCalculatorTest {
    static SimpleCalculator calculator;

    @BeforeClass
    public static void init(){
        calculator = new SimpleCalculator();
    }

    @Test
    public void testSum(){

        assertEquals(calculator.sum(0,0),0);
        assertEquals(calculator.sum(2,3),5);
        assertEquals(calculator.sum(-2,-3),-5);
        assertEquals(calculator.sum(-5,5),0);

    }

    @Test(expected = ArithmeticException.class)
    public void testException(){
        assertEquals(calculator.sum(Integer.MAX_VALUE,1),0);

    }

    @Test
    public void testDiff(){
        assertEquals(calculator.diff(0,0),0);

    }

    @AfterClass
    public static void destroy(){
        calculator = null;
    }

}
