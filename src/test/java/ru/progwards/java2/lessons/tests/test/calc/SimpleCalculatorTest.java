package ru.progwards.java2.lessons.tests.test.calc;

import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.progwards.java2.lessons.tests.SimpleCalculator;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;


@RunWith(Enclosed.class)
public class SimpleCalculatorTest {

    static SimpleCalculator calc;

    @BeforeClass
    public static void init(){calc = new SimpleCalculator();}

    @RunWith(Parameterized.class)
    public static class SumSimpleCalculatorTest {
        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int expected;

        @Parameterized.Parameters(name = "Тест {index}: ({0}) + ({1}) = {2}")
        public static Iterable<Object[]> dataForTest(){
            return Arrays.asList(new Object[][]{
                    {0, 0, 0},
                    {5, 0, 5},
                    {-5, -5, -10},
                    {34, 55, 89},
                    {-34, -55, -89}
            });

        }
        @Test
        public void testWithParams(){assertEquals(expected, calc.sum(val1,val2));}




    }
    public static class SumExceptionTest{
        @Test(expected = ArithmeticException.class)
        public void SumWithException(){calc.sum(Integer.MAX_VALUE, 1);}
    }

    @RunWith(Parameterized.class)
    public static class DiffSimpleCalculatorTest {
        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int expected;

        @Parameterized.Parameters(name = "Тест {index}: ({0}) - ({1}) = {2}")
        public static Iterable<Object[]> dataForTest(){
            return Arrays.asList(new Object[][]{
                    {0, 0, 0},
                    {4, 0, 4},
                    {-5, -5, 0},
                    {34, 55, -21},
                    {-34, -55, 21}
            });

        }
        @Test
        public void testWithParams(){assertEquals(expected, calc.diff(val1,val2));}
    }
    public static class DiffExceptionTest{
        @Test(expected = ArithmeticException.class)
        public void DiffWithException(){calc.diff(Integer.MAX_VALUE, -1);}
    }

    @RunWith(Parameterized.class)
    public static class MultSimpleCalculatorTest {
        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int expected;

        @Parameterized.Parameters(name = "Тест {index}: ({0}) * ({1}) = {2}")
        public static Iterable<Object[]> dataForTest(){
            return Arrays.asList(new Object[][]{
                    {0, 0, 0},
                    {5, 0, 0},
                    {-5, -5, 25},
                    {-4, 5, -20},
                    {-34, -55, 1870}
            });

        }
        @Test
        public void testWithParams(){assertEquals(expected, calc.mult(val1,val2));}
    }
    public static class MultExceptionTest{
        @Test(expected = ArithmeticException.class)
        public void MultWithException(){calc.mult(Integer.MAX_VALUE, 2);}
    }

    @RunWith(Parameterized.class)
    public static class DivSimpleCalculatorTest {
        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int expected;

        @Parameterized.Parameters(name = "Тест {index}: ({0}) / ({1}) = {2}")
        public static Iterable<Object[]> dataForTest(){
            return Arrays.asList(new Object[][]{
                    {4, 4, 1},
                    {0, 5, 0},
                    {-25, -5, 5},
                    {-4, 5, 0},
                    {1800, -55, -32}
            });

        }
        @Test
        public void testWithParams(){assertEquals(expected, calc.div(val1,val2));}
    }
    public static class DivExceptionTest{
        @Test(expected = ArithmeticException.class)
        public void DivWithException(){calc.div(Integer.MAX_VALUE, 0);}
    }

    @AfterClass
    public static void end(){calc = null;}
}
