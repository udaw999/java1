package ru.progwards.java1.lessons.interfaces;


public class CalculateFibonacci {

    private static  CacheInfo lastFibo;

    public static class CacheInfo {

        public int n;
        public int fibo;




    }
//    Если не совпадает - рассчитывать и сохранять в статической переменной lastFibo.
//    не могу это реализовать

    //числа фибоначи
    public static int fiboNumber(int n){
        int i = 1;

        int fib1 = 0;
        int fib2 = 1;
        int fib = 1;

        //System.out.println( "n = " + n);
        if(n == fib){
            System.out.println("fib=n - " + n);
            return fib;

        }
        else {

            lastFibo = new CacheInfo();
            lastFibo.fibo = fib;
            lastFibo.n = i;

        }
        while (i < n) {

            fib = fib1 + fib2;
            fib1 = fib2;
            fib2 = fib;
            i++;


        }

        return fib;
    }

    public static CacheInfo getLastFibo(){
        return lastFibo;
    }
    public static void clearLastFibo(){
        lastFibo = null;
    }

    public static void main(String[] args) {

        //CalculateFibonacci calculateFibonacci = new CalculateFibonacci();
        int n = 29;

//        int i = 1;
//        System.out.print("Числа Фибоначи - ");
//        while (i < n) {
//
//            System.out.print(fiboNumber(i));
//            System.out.print(", ");
//
//            i++;
//        }

        System.out.print(fiboNumber(n));
        System.out.println();
        System.out.println("n= " + lastFibo.n);
        System.out.println("fibo= " + lastFibo.fibo);
    }
}
