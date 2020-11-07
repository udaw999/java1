package ru.progwards.java1.lessons.bigints;

public class IntInteger extends AbsInteger{
    int n;

    public IntInteger(int n){
        this.n = n;
    }

    @Override
    public String toString() {
        return Integer.toString(n);
    }
    public  int getN() {
        return n;
    }
}
