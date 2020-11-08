package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
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
        int valueLength = value.toString().length();//определяем длину числа

       // System.out.println("длина числа - " + value.toString().length());

        //если длина больше n то обрезаем
        if(n < valueLength){
            bool = false;//сообщаем что число больше массива
            //обрезаем методом деления числа BigInteger.TEN.pow(valueLength - n)-- это 10 * на разницу длины и n
           value = value.divide(BigInteger.TEN.pow(valueLength - n));

          //  System.out.println("del - " + value);
        }
        else {
            bool = true;
        }

        //далее заполняем массив
        for (int i = 0; value.compareTo(BigInteger.ZERO) != 0; i++) {

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
            str = digits[i] + str;
        }

        return new BigInteger(str);
    }
    //сложить 2 числа, не используя BigInteger,
    //собираем числа в строке вытаскивая по цифре из массива
    public boolean add(ArrayInteger num) {
        ArrayInteger arrayInteger1 = (ArrayInteger) num;

            int digitsNum = 0;
            int digitsArrayInteger1Num = 0;
            String sum = "";//тут срока суммы чисел из массивов
            int sumNum = 0;
            int cycle = digits.length;//колличество цыклов

        //выбираем большую длину массива для определения числа цыклов
        if (digits.length <= arrayInteger1.digits.length){
            cycle = arrayInteger1.digits.length;
        }

       // System.out.println("digits.length - " + digits.length);


        for (int i=0; i < cycle ;i++) {
            //проверяем оба массива не закончились ли они
            if (digits.length <= i) {
                digitsNum = 0;
            }
            else {
                 digitsNum = digits[i];
            }

            if (arrayInteger1.digits.length <= i) {
                digitsArrayInteger1Num = 0;
            }
            else {
                digitsArrayInteger1Num = arrayInteger1.digits[i];
            }

            //складываем цифры полученные из массивов
            sumNum = sumNum + digitsNum + digitsArrayInteger1Num;
            if(sumNum > 9){
              sum = ( sumNum % 10) + sum;
              sumNum = 1;
            }
            else {
                sum = sumNum + sum;
                sumNum = 0;
            }

        }
        //если при сложении последних цифр sumNum получил 1 то приклеиваем его к началу
        if(sumNum == 1){
            sum = 1 + sum;
        }
        //передаем сумму в fromInt что бы поместить в массив
        fromInt(new BigInteger(sum));

        //если длина массива меньше числа выводим false и заполняем массив 0
        if (bool==false){
            Arrays.fill(digits,(byte)0);
        }

      //  for (byte values : digits) {           System.out.print("," + values);       }
        return bool;
    }

    public static void main(String[] args) {
        // byte[] digits = {0,0,0,0,0,0};
        int z = 6;
        ArrayInteger arrayInteger = new ArrayInteger(20);
        BigInteger value = BigInteger.valueOf(999996234567899999l);
        arrayInteger.fromInt(value);

        ArrayInteger arrayInteger2 = new ArrayInteger(20);
        BigInteger value2 = BigInteger.valueOf(99999996095555555l);
        arrayInteger2.fromInt(value2);


        System.out.println(arrayInteger.add(arrayInteger2));
        System.out.println("toInt 1 - " + arrayInteger.toInt());

//        BigInteger n1 = BigInteger.valueOf(934565555555l);
//        BigInteger n2 = BigInteger.valueOf(56789);
//        System.out.println(n1 + " + " + n2 + " = " + n1.add(n2));

       // System.out.println(value.add(value2));


    }

}
