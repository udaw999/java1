package ru.progwards.java1.lessons.compare_if_cycles;

public class CyclesGoldenFibo {

    public static boolean containsDigit(int number, int digit){

        return number == digit;
    }

    public static int fiboNumber(int n){
        int i = 1;

        int fib1 = 0;
        int fib2 = 1;
        int fib = 1;



            while (i < n) {

                fib = fib1 + fib2;
                fib1 = fib2;
                fib2 = fib;
                i++;
            }

        return fib;
    }

    public static boolean isGoldenTriangle(int a, int b, int c){


        return (a == b && 1.61703 < (double)a / (double)c &&  (double)a / (double)c <  1.61903 || b == c && 1.61703 < (double)b / (double)a &&  (double)b / (double)a <  1.61903 || a == c && 1.61703 < (double)a / (double)b &&  (double)a / (double)b <  1.61903);

    }

    public static void main(String[] args){


        int i = 1;
        System.out.print("Числа Фибоначи - ");
        while (i < 16) {

            System.out.print(fiboNumber(i));
            System.out.print(", ");

            i++;
        }
        System.out.println();
        System.out.println();

//Золотые треугольники
         int z=1;
         while (z<=100) {
             z++;
             int y=1;
             while (y<=100) {
                 y++;
                 if(isGoldenTriangle(y,y,z))
                     System.out.println("основание - "+z+"   рёбра - "+y);

             }
         }
    }

}
