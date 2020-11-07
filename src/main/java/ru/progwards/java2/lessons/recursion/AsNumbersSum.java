package ru.progwards.java2.lessons.recursion;

import java.util.ArrayList;

public class AsNumbersSum {
    static ArrayList<Integer> list = new ArrayList<>();
    static String rezult = "";
    public static int number;

    private static String asNumbersSum(int number){
        AsNumbersSum.number = number;
        asNumber(number, number, 0);
        return rezult;
    }


    static void  print (int j,  int i){
        if (j<i){
            if (j != 0)
                rezult += " + ";

            rezult += list.get(j);
            print(j+1 ,i);
        } else {
            return;
        }

    }

    static void asNumber(int n, int k, int i) {
        if ( n < 0 ) return;
        if ( n == 0 ) {
            print(0,i);

            if (number != i)
                rezult += " = ";
        }
        else {
            if ( n >= k) {
                list.add(i,k);
                asNumber(n - k, k, i + 1);
            }
            if ( k > 1) asNumber(n, k - 1, i);
        }
    }


    public static void main(String[] args) {
        System.out.println("asNumbersSum:  " + asNumbersSum(10));

    }
}
