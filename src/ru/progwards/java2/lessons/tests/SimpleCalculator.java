package ru.progwards.java2.lessons.tests;

public class SimpleCalculator implements ISimpleCalculator {
    @Override
    public int sum(int val1, int val2) {
        long result = (long) val1 + val2;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            throw new ArithmeticException();
        return val1 + val2;
    }

    @Override
    public int diff(int val1, int val2) {
        long result = (long) val1 - val2;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            throw new ArithmeticException();
        return val1 - val2;
    }

    @Override
    public int mult(int val1, int val2) {
        long result = (long) val1 * val2;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            throw new ArithmeticException();
        return val1 * val2;
    }

    @Override
    public int div(int val1, int val2) {
        long result = (long) val1 / val2;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE || val2 == 0)
            throw new ArithmeticException();
        return val1 / val2;
    }


}

