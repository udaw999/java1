package ru.progwards.java1.lessons.bitsworld;

public class SumBits {

    public static int sumBits(byte value){
        int sum = 0;

        for (int i=0; i < 8; i++){
            sum = sum + (value & 1);
           value >>= 1;

           if(value==0)
               break;


        }

        return sum;
    }

    public static void main(String[] args) {
        byte value = -128;
        System.out.println(sumBits(value));
        System.out.println();
        //sumBits(value);
    }
}
