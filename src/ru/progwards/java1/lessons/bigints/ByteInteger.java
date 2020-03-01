package ru.progwards.java1.lessons.bigints;

public  class ByteInteger extends AbsInteger{
    byte n;

    public ByteInteger(byte n){
        this.n = n;
    }
    @Override
    public String toString() {
        return Integer.toString(n);
    }
}
