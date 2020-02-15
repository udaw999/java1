package ru.progwards.java1.lessons.bitsworld;

public class SumBits {

    public static int sumBits(byte value){
        int sum = 0;
        for (int i=0; 1<=value; i++){
            sum = sum + (value & 1);
           value >>= 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        byte value = 0b00001101111;
        System.out.println(sumBits(value));
        System.out.println();
        sumBits(value);
    }
}
