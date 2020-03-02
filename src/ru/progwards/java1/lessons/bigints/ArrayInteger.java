package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;

public class ArrayInteger {

    byte[] digits;
    int n;
    //размер массива
    public ArrayInteger(int n){

        this.n =n;

    }

    public void fromInt(BigInteger value){
        digits = new byte[n];
        for (int i=0; value.compareTo(BigInteger.ZERO) != 0; i++) {

            System.out.println(value);
            //получаем остаток деления на 10 и приводим к строке
            String str = "" + value.mod(BigInteger.TEN);
            //помещаем в  массив преобразовав число из String в Byte
            digits[i] = Byte.parseByte(str);
            //делим  BigInteger value на 10
            value = value.divide(BigInteger.TEN);

        }

    }

    public static void main(String[] args) {
       // byte[] digits = {0,0,0,0,0,0};
        int z = 6;
        ArrayInteger arrayInteger = new ArrayInteger(6);
        BigInteger value = BigInteger.valueOf(123457);
   /*     int val = 123456;
        for (int i=0; val > 0; i++) {
//            System.out.println(value);
//            value = value.divide(BigInteger.TEN);
            System.out.println(val);
            int n = val % 10;
            val = val / 10;
            digits[i] = (byte)n;
            System.out.println(n);

        }
        for (byte b : digits) {
            System.out.print(b + ", ");
        }*/


        //System.out.println(arrayInteger.fromInt(value));




    }
}
