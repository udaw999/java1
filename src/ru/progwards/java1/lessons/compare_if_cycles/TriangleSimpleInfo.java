package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleSimpleInfo {
    public static int maxSide(int a, int b, int c){
        int z;
        if (a >= b && a >= c  )
            z = a;
        else if (b >= a && b >= c)
            z = b;
        else
            z = c;


        return z;
    }

    public static int minSide(int a, int b, int c){
        int z;
        if (a <= b && a <= c  )
            z = a;
        else if ( b <= c)
            z = b;
        else
            z = c;


        return z;
    }

    public static boolean isEquilateralTriangle(int a, int b, int c){
        return (a == b && b == c);
    }

    public static void main(String[] args){
        System.out.println(maxSide(40,58,60));
        System.out.println(minSide(22,22,33));
        System.out.println(isEquilateralTriangle(23,23,23));
    }
}
