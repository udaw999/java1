package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleInfo {

    public static boolean isTriangle(int a, int b, int c){
        return (a + b > c && b + c > a && a + c > b);
    }

    public static boolean isRightTriangle(int a, int b, int c){
        return (a * a + b * b == c * c || b * b + c * c == a * a || a * a + c * c == b * b );
    }

    public static boolean isIsoscelesTriangle(int a, int b, int c){
        return (a == b || b == c || a == c );
    }

    public static void main(String[] args){
        System.out.println(isTriangle(2,26,27));
        System.out.println(isRightTriangle(5,3,4));
        System.out.println(isIsoscelesTriangle(5,6,5));
    }

}
