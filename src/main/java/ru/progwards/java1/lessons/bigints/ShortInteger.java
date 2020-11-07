package ru.progwards.java1.lessons.bigints;

public class ShortInteger extends AbsInteger{
    short n;

    public ShortInteger(short n){
        this.n = n;
    }
    @Override
    public String toString() {
        return Integer.toString(n);
    }

     //“get” — “получать” (т.е. “метод для получения значения поля”)

    public  int getN() {
        return n;
    }
}
