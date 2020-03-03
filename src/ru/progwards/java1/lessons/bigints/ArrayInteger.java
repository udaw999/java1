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
    //Возможно ошибка в задании или я не знаю решения . но в массиве надо чтоб цифри шли в том же порядке
    //для этого пришлось сперва перевернуть число
    public void fromInt(BigInteger value) {
        digits = new byte[n];
        String strValue = "";//перевернутое число в строке

        //переворачиваю число
        for (int j=0; value.compareTo(BigInteger.ZERO) != 0; j++){

            //получаем остаток деления на 10 и приводим к строке
            String strNum = "" + value.mod(BigInteger.TEN);
            //собираем в строку перевернутое число
            strValue = strValue + strNum;

            value = value.divide(BigInteger.TEN);
        }
        //возвращаем число-сстроку- перевернутую в прежнюю переменную в BigInteger
        value = BigInteger.valueOf(Long.parseLong(strValue));
        //далее заполняем массив
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
//        for (byte values : digits) {           System.out.print("," + values);       }
//        System.out.println();
    }

    public BigInteger toInt() {

        String str = "";
        //собираем число в строку из массива
        for (int i = 0; i < n; i++) {
            str = str + digits[i];
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

            num1 =  num1 + digits[i];
        }
        for (int i=0; i < arrayInteger1.digits.length; i++){

            num2 = num2 + arrayInteger1.digits[i];
        }
        //складываем числа переведя из строки в Integer
        System.out.println("num1 - " + Long.parseLong(num1));
        System.out.println("num2 - " + Long.parseLong(num2));

        long sum = Long.parseLong(num1) + Long.parseLong(num2);
        //отправляем число в массив
        System.out.println("sum - " + sum);
        fromInt(BigInteger.valueOf(sum));


        if (bool==false){
            Arrays.fill(digits,(byte)0);
        }
        System.out.println("add");
        for (byte values : digits) {           System.out.print("," + values);       }
        return bool;
    }

    public static void main(String[] args) {
        // byte[] digits = {0,0,0,0,0,0};
        int z = 6;
        ArrayInteger arrayInteger = new ArrayInteger(7);
        BigInteger value = BigInteger.valueOf(1202189);
        arrayInteger.fromInt(value);

        ArrayInteger arrayInteger2 = new ArrayInteger(5);
        BigInteger value2 = BigInteger.valueOf(36099);
        arrayInteger2.fromInt(value2);


        System.out.println(arrayInteger.add(arrayInteger2));
        System.out.println(arrayInteger.toInt());

//        BigInteger n1 = BigInteger.valueOf(934565555555l);
//        BigInteger n2 = BigInteger.valueOf(56789);
//        System.out.println(n1 + " + " + n2 + " = " + n1.add(n2));

       // System.out.println(value.add(value2));

    }

}
