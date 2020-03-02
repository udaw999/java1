package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.sql.Array;
import java.util.Arrays;

public class ArrayInteger {

    byte[] digits;
    int n;
    boolean bool = true;
    //размер массива
    public ArrayInteger(int n) {

        this.n = n;

    }

    //(уложить BigInteger во внутренний массив)
    //Например, число 159 должно занять 3 ячейки массива digits[0] = 9; digits[1] = 5; digits[2] = 1;
    public void fromInt(BigInteger value) {
        digits = new byte[n];

        for (int i = 0; value.compareTo(BigInteger.ZERO) != 0; i++) {
            if (i>n-1){
                //проверка При переполнении массива вернуть false, при этом само число сбросить в 0
                //Arrays.fill(digits,(byte)0);
                bool = false;
                break;
            }

            //получаем остаток деления на 10 и приводим к строке
            String str = "" + value.mod(BigInteger.TEN);
            //помещаем в  массив преобразовав число из String в Byte
            digits[i] = Byte.parseByte(str);
            //делим  BigInteger value на 10
            value = value.divide(BigInteger.TEN);

        }
        for (byte values : digits) {           System.out.print("," + values);       }
        System.out.println();
    }

    public BigInteger toInt() {

        String str = "";
        //собираем число в строку из массива
        for (int i = 0; i < n; i++) {
            str = digits[i] + str;
        }

        return BigInteger.valueOf(Integer.parseInt(str));
    }
    //сложить 2 числа, не используя BigInteger,
    //собираем числа в строке вытаскивая по цифре из массива
    public boolean add(ArrayInteger num) {
        ArrayInteger arrayInteger1 = (ArrayInteger) num;


        String num1 = "";
        String num2 = "";

        for (int i=0; i < digits.length; i++){

            num1 = digits[i] + num1;
        }
        for (int i=0; i < arrayInteger1.digits.length; i++){

            num2 = arrayInteger1.digits[i] + num2;
        }
        //складываем числа переведя из строки в Integer
        long sum = Integer.parseInt(num1) + Integer.parseInt(num2);
        //отправляем число в массив
        fromInt(BigInteger.valueOf(sum));
        if (bool==false){
            Arrays.fill(digits,(byte)0);
        }
//        System.out.println("add");
//        for (byte values : digits) {           System.out.print("," + values);       }
        return bool;
    }

    public static void main(String[] args) {
        // byte[] digits = {0,0,0,0,0,0};
        int z = 6;
        ArrayInteger arrayInteger = new ArrayInteger(10);
        BigInteger value = BigInteger.valueOf(5555555565l);
        arrayInteger.fromInt(value);

        ArrayInteger arrayInteger2 = new ArrayInteger(8);
        BigInteger value2 = BigInteger.valueOf(567895555);
        arrayInteger2.fromInt(value2);


        System.out.println(arrayInteger.add(arrayInteger2));
        System.out.println(value.add(value2));
    }

}
