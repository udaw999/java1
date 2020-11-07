package ru.progwards.java1.lessons.basics;

public class AccuracyDoubleFloat {
//4/3πR3
    public static double volumeBallDouble(double radius){
        return 4.0 / 3 * 3.14 * radius * radius * radius;
    }

    public static float volumeBallFloat(float radius){
        return (4.0f / 3) * 3.14f * radius * radius * radius;
    }

    public static double calculateAccuracy(double radius){
        return volumeBallDouble(radius) - volumeBallFloat((float) radius);
    }

    public static void main(String[] args) {
        System.out.println(volumeBallDouble(6371.2));
        System.out.println(volumeBallFloat(6371.2f));
        System.out.println(calculateAccuracy(6371.2));
    }
}
