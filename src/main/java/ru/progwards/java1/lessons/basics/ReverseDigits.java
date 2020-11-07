package ru.progwards.java1.lessons.basics;

public class ReverseDigits {
    public static void println(String str){
        System.out.println(str);
    }
    public static int reverseDigits(int number){
        // переворачиваем трехзначное число
//        int c = number / 100;
//        int b = number / 10 % 10;
//        int a = number % 10;
//        return a * 100 + b * 10 + c;
        return number % 10 * 100 + number / 10 % 10 * 10 + number / 100;
    }

    public static void main(String[] args){
        System.out.println(reverseDigits(123));
    }

}
