package ru.progwards.java1.lessons.register1;

public class Counter {
    public static void inc(ByteRegister value){
        System.out.println(value);
        int valueByte = Integer.parseInt(value.toDecString());
        System.out.println(valueByte);
        valueByte++;
        value.toDecString();
        System.out.println(value.toDecString());
        System.out.println(valueByte);
    }
    public static void dec(ByteRegister value){
        int valueByte = Integer.parseInt(value.toString());
        valueByte--;

    }

    public static void main(String[] args) {

        inc(new ByteRegister((byte) 0));
    }
}
