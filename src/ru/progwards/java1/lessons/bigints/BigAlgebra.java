package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigAlgebra {

    //алгоритм быстрого возведения в степень pow числа num в BigDecimal
    public static  BigDecimal fastPow(BigDecimal num, int pow){
        //переводим степень pow в двоичную систему
        //сдвигаем в цыкле
        BigDecimal  result = BigDecimal.valueOf(1);
        for (int i=0; 0 < pow; i++){

            if((pow & 1) == 1){
                result = result.multiply(num);
            }
            num = num.multiply(num);

            pow >>= 1;

        }

    return result;

    }

    //алгоритм вычисления n-го числа фибоначчи в BigInteger.
    public static BigInteger fibonacci(int n){

        int i = 1;

        BigInteger fib1 = BigInteger.valueOf(0);
        BigInteger fib2 = BigInteger.valueOf(1);
        BigInteger fib = BigInteger.valueOf(1);



        while (i < n) {

            fib = fib1.add(fib2);
            fib1 = fib2;
            fib2 = fib;
            i++;
        }

        return fib;
    }


    public static void main(String[] args) {
    BigAlgebra bigAlgebra = new BigAlgebra();

        System.out.print("возведение в степень числа - ");
        System.out.println(fastPow(BigDecimal.valueOf(4),7));

        int i = 1;
        System.out.print("Числа Фибоначи - ");
        while (i < 16) {

            System.out.print(fibonacci(i));
            System.out.print(", ");

            i++;
        }
    }
}
