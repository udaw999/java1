package ru.progwards.java1.lessons.register1;

public class Counter {
    public static void inc(ByteRegister value){
        System.out.println(value.toDecString());
        System.out.println(value);
        int valueByte = Integer.parseInt(value.toDecString());
        System.out.println(valueByte);
        ++valueByte;
        value.toDecString();
        value = new ByteRegister((byte)valueByte);

        System.out.println("do valueByte" + valueByte);

        System.out.println("pos valueByte" + valueByte);

    }
    public static void dec(ByteRegister value){
        int valueByte = Integer.parseInt(value.toString());
        valueByte--;

    }

    public static void main(String[] args) {
        ByteRegister b = new ByteRegister((byte) 100);

        inc(b);
        System.out.println("вызов внизу " + b.toDecString());
    }
}
