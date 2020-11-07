package ru.progwards.java1.lessons.bitsworld;

public class CheckBit {

    public static int checkBit(byte value, int bitNumber){
        for (int i=0; i<bitNumber; i++){
            value >>= 1;
        }

        return value &= 1;
    }

    public static void main(String[] args) {
        byte value = 2;
        System.out.println(checkBit(value,0));
        System.out.println();
        System.out.println(Integer.toBinaryString(-128));
    }
}
